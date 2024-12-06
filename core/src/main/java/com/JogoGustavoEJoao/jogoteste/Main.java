package com.JogoGustavoEJoao.jogoteste;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img, tNave, tMissile, tEnemy;
    private Sprite nave, missile;
    private float posX, posY, velocity, xMissile, yMissile;
    private boolean attack, gameover;
    private Array<Rectangle> enemies;
    private long lastEnemyTime;
    private int score, power, numEnemies;
    private int questionIndex;

    private BitmapFont bitmap;

    private Array<Pergunta> Perguntas;
    private Pergunta currentQuestion;
    private Array<EnemyAnswerPair> enemyAnswerPairs;

    @Override
    public void create() {
        Perguntas = new Array<>();
        Perguntas.add(new Pergunta(
            "Em que ano a cidade de Santa Maria foi fundada?",
            new String[]{"1900", "1857", "1800"},
            1 // Resposta correta: 1857
        ));
        Perguntas.add(new Pergunta(
            "Santa Maria também é conhecida como:",
            new String[]{"Cidade dos Ventos", "Capital do Sul", "Coração do Rio Grande"},
            2 // Resposta correta: Coração do Rio Grande
        ));
        Perguntas.add(new Pergunta(
            "A Quarta Colônia é composta por quantos municípios?",
            new String[]{"7", "11", "9"},
            2)); // Resposta correta: 9

        Perguntas.add(new Pergunta(
            "A Quarta Colônia foi formada por imigrantes de que nacionalidade?",
            new String[]{"Alemães", "Italianos", "Espanhóis"},
            1)); // Resposta correta: Italianos

        Perguntas.add(new Pergunta(
            "Quando foi a última vez que descobriram um fóssil na Quarta Colônia?",
            new String[]{"Maio/2024", "Abril/2023", "Junho/2022"},
            0)); // Resposta correta: Maio/2024

        Perguntas.add(new Pergunta(
            "Qual é o nome da área residencial de Santa Maria que foi construída para abrigar construtores de ferrovias no início do século XX?",
            new String[]{"Vila Operária", "Vila Belga", "Vila Industrial"},
            1)); // Resposta correta: Vila Belga

        Perguntas.add(new Pergunta(
            "Quem é a padroeira da cidade de Santa Maria?",
            new String[]{"Nossa Senhora Aparecida", "Nossa Senhora da Conceição", "Nossa Senhora Medianeira"},
            1)); // Resposta correta: Nossa Senhora da Conceição

        Perguntas.add(new Pergunta(
            "Qual é o evento religioso mais famoso da cidade de Santa Maria?",
            new String[]{"Romaria", "Procissão do Encontro", "Festa de Nossa Senhora"},
            0)); // Resposta correta: Romaria

        Perguntas.add(new Pergunta(
            "A Romaria celebra qual santa?",
            new String[]{"Nossa Senhora Medianeira", "Nossa Senhora da Conceição", "Nossa Senhora Aparecida"},
            0)); // Resposta correta: Nossa Senhora Medianeira

        Perguntas.add(new Pergunta(
            "Santa Maria é reconhecida como um polo:",
            new String[]{"Agrícola e Logístico", "Industrial e Comercial", "Militar e Educacional"},
            2)); // Resposta correta: Militar e Educacional

        Perguntas.add(new Pergunta(
            "Santa Maria também é conhecida como ____________ por ter uma localização estratégica no centro do estado.",
            new String[]{"Cidade dos Caminhos", "Coração do Rio Grande", "Centro do Sul"},
            0)); // Resposta correta: Cidade dos Caminhos

        Perguntas.add(new Pergunta(
            "Qual é o evento cultural de mais destaque na Quarta Colônia?",
            new String[]{"Festival de Dança", "Festival do Imigrante", "Festa da Colônia"},
            1)); // Resposta correta: Festival do Imigrante

        Perguntas.add(new Pergunta(
            "Qual é o clube de Santa Maria conhecido por ser fundado por ferroviários?",
            new String[]{"Internacional-SM", "Grêmio Novorizonte", "Riograndense"},
            2)); // Resposta correta: Riograndense

        Perguntas.add(new Pergunta(
            "Sobre o turismo na Quarta Colônia, se destacam:",
            new String[]{"Natureza e gastronomia", "Comércio e eventos", "Arte e música"},
            0)); // Resposta correta: Natureza e gastronomia

        Perguntas.add(new Pergunta(
            "Qual o evento cultural mais conhecido em Santa Maria?",
            new String[]{"Feira do Livro", "Festival de Música", "Semana de Arte"},
            0)); // Resposta correta: Feira do Livro

        Perguntas.add(new Pergunta(
            "Como é alcunhado o Esporte Clube Internacional de Santa Maria?",
            new String[]{"Alvinegro", "Vermelhão", "Alvirrubro"},
            2)); // Resposta correta: Alvirrubro

        Perguntas.add(new Pergunta(
            "Qual a população aproximada de Santa Maria?",
            new String[]{"271.000", "300.000", "250.000"},
            0)); // Resposta correta: 271.000

        Perguntas.add(new Pergunta(
            "Qual árvore é considerada o símbolo de Santa Maria?",
            new String[]{"Ipê-Roxo", "Araucária", "Figueira"},
            0)); // Resposta correta: Ipê-Roxo

        Perguntas.add(new Pergunta(
            "Qual a população aproximada da Quarta Colônia?",
            new String[]{"35.000", "58.000", "72.000"},
            1)); // Resposta correta: 58.000

        Perguntas.add(new Pergunta(
            "Quais as cidades com mais habitantes da Quarta Colônia?",
            new String[]{"Silveira Martins e São João do Polêsine", "Agudo e Restinga Seca", "Nova Palma e Dona Francisca"},
            1)); // Resposta correta: Agudo e Restinga Seca

        Perguntas.add(new Pergunta(
            "Quantas espécies foram descobertas por fósseis encontrados na Quarta Colônia?",
            new String[]{"40 espécies", "35 espécies", "30 espécies"},
            1)); // Resposta correta: 35 espécies

        Perguntas.add(new Pergunta(
            "José Mariano da Rocha Filho foi importante para a cidade de Santa Maria por:",
            new String[]{"Criar o primeiro jornal da cidade", "Fundar a UFSM", "Ser prefeito da cidade"},
            1)); // Resposta correta: Fundar a UFSM

        Perguntas.add(new Pergunta("Qual é o nome do parque em Santa Maria que preserva a memória ferroviária da cidade?"
        ,new String[]{"Parque Chacará das Flores", "Parque Ibirapuera","Parque Itaimbé"},
            2)); // Resposta Correta: Parque Itaimbé

        questionIndex = 0;
        currentQuestion = Perguntas.get(questionIndex);

        batch = new SpriteBatch();
        img = new Texture("bg.png");
        tNave = new Texture("spaceship.png");
        nave = new Sprite(tNave);
        posX = 0;
        posY = 0;
        velocity = 10;

        tMissile = new Texture("missile.png");
        missile = new Sprite(tMissile);
        xMissile = posX;
        yMissile = posY;
        attack = false;

        tEnemy = new Texture("enemy.png");
        enemies = new Array<>();
        lastEnemyTime = 0;

        score = 0;
        power = 3;

        bitmap = new BitmapFont();
        bitmap.setColor(Color.WHITE);

        spawnEnemies();
        gameover = false;
    }

    @Override
    public void render() {
        this.moveNave();
        this.moveMissile();
        this.checkCollisions();

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);

        if (!gameover) {
            if (attack) {
                batch.draw(missile, xMissile, yMissile);
            }
            batch.draw(nave, posX, posY);

            for (EnemyAnswerPair pair : enemyAnswerPairs) {
                Rectangle enemy = pair.enemy;
                batch.draw(tEnemy, enemy.x, enemy.y, enemy.width, enemy.height);
                bitmap.draw(batch, pair.answer, enemy.x + 120, enemy.y + enemy.height / 2 );
            }

            bitmap.draw(batch, "Pergunta: " + currentQuestion.text, 20, 150);
            bitmap.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
            bitmap.draw(batch, "Power: " + power, Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 20);
        } else {
            bitmap.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
            bitmap.draw(batch, "Pressione ENTER para reiniciar", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 20);

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                resetGame();
            }
        }

        batch.end();
    }


    private void resetGame() {
        score = 0;
        power = 3;
        questionIndex = 0;
        currentQuestion = Perguntas.get(questionIndex);
        spawnEnemies();
        gameover = false;
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        tNave.dispose();
        tMissile.dispose();
        tEnemy.dispose();
        bitmap.dispose();
    }

    private void moveNave() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (posX < Gdx.graphics.getWidth() - nave.getWidth()) {
                posX += velocity;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (posX > 0) {
                posX -= velocity;
            }
        }
    }

    private void checkCollisions() {
        Iterator<EnemyAnswerPair> iter = enemyAnswerPairs.iterator();

        while (iter.hasNext()) {
            EnemyAnswerPair pair = iter.next();
            Rectangle enemy = pair.enemy;


            if (collide(enemy.x, enemy.y, enemy.width, enemy.height, xMissile, yMissile, missile.getWidth(), missile.getHeight()) && attack) {
                attack = false;
                if (pair.answer.equals(currentQuestion.answers[currentQuestion.correctAnswerIndex])) {
                    score += 10;
                    Gdx.app.log("Resposta", "Correta!");

                    iter.remove();

                    questionIndex++;
                    if (questionIndex < Perguntas.size) {
                        currentQuestion = Perguntas.get(questionIndex);
                        spawnEnemies();
                    } else {
                        gameover = true;
                    }
                } else {
                    power--;
                    Gdx.app.log("Resposta", "Errada!");

                    iter.remove();
                }

                if (power <= 0) {
                    gameover = true;
                }
                break;
            }
        }
    }


    private void moveMissile() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !attack) {
            attack = true;
            xMissile = posX + nave.getHeight() / 2 - 12;
        }

        if (attack) {
            if (yMissile < Gdx.graphics.getWidth()) {
                yMissile += 15;
            } else {
                yMissile = posY + nave.getWidth() / 2;
                attack = false;
            }
        } else {
            yMissile = posY + nave.getWidth() / 2;
            xMissile = posX + nave.getHeight() / 2 - 12;
        }
    }

    private void spawnEnemies() {
        enemyAnswerPairs = new Array<>();

        float enemySpacing = Gdx.graphics.getWidth() / 4.0f;
        float enemyWidth = tEnemy.getWidth() * 1.5f;
        float enemyHeight = tEnemy.getHeight() * 1.5f;

        for (int i = 0; i < currentQuestion.answers.length; i++) {
            float enemyX = enemySpacing * (i + 1) - enemyWidth / 2;
            float enemyY = Gdx.graphics.getHeight() - enemyHeight;

            Rectangle enemy = new Rectangle(enemyX, enemyY, enemyWidth, enemyHeight);
            enemyAnswerPairs.add(new EnemyAnswerPair(enemy, currentQuestion.answers[i]));
        }
    }


    private boolean collide(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        return x1 + w1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2;
    }
}

