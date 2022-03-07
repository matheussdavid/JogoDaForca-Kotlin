fun main(args: Array<String>) {

  class Jogo(Palavra: String, Dica: String){
    var chances = 6
    var tentativas = 0
    var statusJogo = true
    var ganhar = false
    var palavraEscondida =  arrayListOf<String>()
    var letrasUsadas = mutableListOf<String>()
    val palavra = Palavra
    val dica = Dica

    fun tamanhoPalavra(palavra: String): Int{
      return palavra.length
    }

    fun letrasDistintas(palavra: String): String{
      var letrasDist = palavra.lowercase().chars().distinct().count().toString()
      return "A palavra possui $letrasDist letras distintas"

    }

    fun buscaLetra(letra: String, palavra: String){
      for((i, item) in palavra.withIndex()){
        if(letra == palavra[i].toString()){
          palavraEscondida[i] = letra
        }
      }
    }

    fun ganhou(palavra: String, palavraEscondida: String){
      if(palavra.equals(palavraEscondida)){
        ganhar = true
      }
    }

  }


  var palavra: String
  var dica: String
  println("------- Jogo da Forca 1.0 -------")
  print("Informe a palavra a ser descoberta: ")
  palavra = readLine().toString().lowercase()
  print("Informe a dica da palavra: ")
  dica = readLine().toString()
  val jogo = Jogo(palavra, dica)

  for(letra in jogo.palavra){
    jogo.palavraEscondida.add("*")
  }


  println("A palavra possui ${jogo.tamanhoPalavra(palavra)} letras")
  println(jogo.letrasDistintas(palavra))

  var letra: String
  while(jogo.statusJogo == true){
    jogo.ganhou(palavra, jogo.palavraEscondida.joinToString(""))
    if(jogo.chances >=1 && jogo.ganhar == false ){
      println("--------------------------------\n")
      println("${jogo.chances}/6 chances disponíveis")
      println("PALAVRA: ${jogo.palavraEscondida}")
      println("DICA: $dica")
      print("Informe a letra a ser procurada: ")
      letra = readLine().toString().lowercase()
      if(palavra.contains(letra) && !jogo.letrasUsadas.contains(letra)){
        jogo.buscaLetra(letra, palavra)
        jogo.letrasUsadas.add(letra)
      }
      else if(jogo.letrasUsadas.contains(letra)){
        println("A letra: $letra já foi utilizada, tente com outra!!!")
        println("Letras utilizadas: ${jogo.letrasUsadas}")
      }
      else{
        println("Letra incorreta, tente novamente")
        jogo.letrasUsadas.add(letra)
        println("Letras utilizadas: ${jogo.letrasUsadas}")
        jogo.chances--
      }
    }
    else{
      if(jogo.ganhar == true){
        println("\n\n!!! VENCEDOR !!!")
        println("Parabéns, você acertou a palavra $palavra! e te restavam ${jogo.chances} chances")
        break

      }
      else{
        println("--------------------------------\n")
        jogo.statusJogo = false
        println("Você estourou o número de tentativas, Fim de Jogo!")
      }

    }

  }

}
