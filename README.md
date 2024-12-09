# Jogo João e Gustavo
* Integrantes: Gustavo Machado Teixeira, João Antônio B. Lobler
* Curso: Sistemas de Informação

## Ideia do jogo:
* O jogo é uma aventura interativa que combina ação e conhecimento sobre a região central do Rio Grande do Sul. O jogador assume o papel de um explorador destemido que precisa derrotar inimigos ao longo do caminho. Cada inimigo traz uma pergunta desafiadora sobre a cultura, história e curiosidades da região. Para vencer, o jogador deve responder corretamente, atirando no inimigo que exibe a resposta certa.

## Desenvolvimento

### Fase inicial:
* Inicialmente buscamos entender como funcionava a biblioteca. Entendendo seus conceitos básicos, desde como iniciar e rodar um projeto, até a parte de movimentos iniciais como colocar uma imagem como plano de fundo.

### Primeiro commit:

* O primeiro commit dado no projeto se tratava de uma versão extremamente embrionária do projeto que constava mais como um teste das funcionalidades do Libgdx aprendidas na internet(Movimentação de personagens, criação de inimigos, escrita na tela) que propriamente uma versão do que posteriormente seria o jogo.

* Nesse primeiro commit vale destacar a primeira expêriencia com palavras desconhecidas como `batch`, `texture` e `Sprite`
* Foi entendido por nós que o `batch` era utilizado para fazer desenhos na tela, o `texture` para carregar um `.png` que posteriormente seria usado no jogo, e o `sprite` para fazer com que as imagens "ganhem vida"

### Segunda fase do desenvolvimento:

* Em um segundo momento com a ideia já mais estruturada e um entendimento melhor sobre a nova biblioteca, começamos de fato a fazer o nosso jogo com a nossa ideia de um explorador que "atacaria" as respostas. 
* Função `spawnEnemies`: Agora, a função foi reformulada para gerar três inimigos posicionados no topo da tela, em vez de criar múltiplos inimigos aleatórios que se moviam na direção do explorador.
* Função `moveNave`: A lógica de movimento do explorador foi ajustada para permitir que ele se mova apenas horizontalmente, tornando o controle mais simples e intuitivo.
* Função `moveMissile`: A função responsável pelos disparos do explorador foi modificada para garantir que os tiros sigam a direção correta, alinhados com a lógica do desafio proposto.


### Funções de colisão

* Uma das partes mais difíceis do desenvolvimento deste projeto foi fazer as funções de colisão.
* O primeiro passo foi fazer uma função para verificar se o retângulo do míssil se sobrepõe ao retângulo de algum inimigo. A função `collide` analisa se as bordas de um retângulo e outro se sobrepõem

 `private boolean collide(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
  return x1 + w1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2;
  }`

* A verificação da colisão e consêquencias dessa colisão acontecem na função `checkCollisions`



* Para cada inimigo, verifica se o míssil está colidindo com ele usando a função `collide`.
Se o míssil colidir, o ataque é desativado (`attack = false`).
Se o inimigo contiver a resposta correta, a pontuação aumenta.
Se a resposta for errada, o jogador perde poder (`power--`).
O inimigo é removido da lista de inimigos.
Se o poder do jogador acabar, o jogo termina (`gameover = true`).







### Referências:

* https://libgdx.com/wiki/start/project-generation
* https://www.youtube.com/watch?v=VF6N_X_oWr0
* https://libgdx.com/wiki/input/simple-text-input
* https://stackoverflow-com.translate.goog/questions/51013197/libgdx-internal-calls-to-mathutils-random-interfere-with-the-sequence-of-rando?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt-BR&_x_tr_pto=sc
* https://libgdx.com/wiki/math-utils/math-utilities
* https://youtube.com/playlist?list=PLUJBQEDDLNclxZvKTT2Icq9aVBPqLRMCA&si=YnfaG3oQg-8QN8zc
* https://www.locaweb.com.br/blog/temas/codigo-aberto/git-pull-aprenda-a-usar-o-comando/

