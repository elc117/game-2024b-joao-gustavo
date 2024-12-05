package com.JogoGustavoEJoao.jogoteste;

public class Pergunta {
        public String text; // Texto da pergunta
        public String[] answers; // Respostas possíveis
        public int correctAnswerIndex; // Índice da resposta correta

        public Question(String text, String[] answers, int correctAnswerIndex) {
            this.text = text;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
        }
}
