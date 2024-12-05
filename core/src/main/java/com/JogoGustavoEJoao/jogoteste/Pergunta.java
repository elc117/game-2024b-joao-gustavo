package com.JogoGustavoEJoao.jogoteste;

public class Pergunta {
        public String text;
        public String[] answers;
        public int correctAnswerIndex;

        public Pergunta(String text, String[] answers, int correctAnswerIndex) {
            this.text = text;
            this.answers = answers;
            this.correctAnswerIndex = correctAnswerIndex;
        }
}
