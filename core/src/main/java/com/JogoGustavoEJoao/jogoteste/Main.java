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

    private BitmapFont bitmap;

    private Array<Pergunta> Perguntas;
    private Pergunta currentQuestion;

    @Override
    public void create() {
        Perguntas = new Array<>();
        Perguntas.add(new Pergunta("Qual é a cor do céu?", new String[]{"Azul", "Verde", "Amarelo"}, 0)
        );
        currentQuestion = Perguntas.get(0);


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
        numEnemies = 799999999;


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

            // Desenha inimigos com respostas
            for (int i = 0; i < enemies.size; i++) {
                Rectangle enemy = enemies.get(i);
                batch.draw(tEnemy, enemy.x, enemy.y, enemy.width, enemy.height);
                bitmap.draw(batch, currentQuestion.answers[i], enemy.x + 80, enemy.y + enemy.height / 2);
            }

            // Exibe a pergunta na parte inferior
            bitmap.draw(batch, "Pergunta: " + currentQuestion.text, 20, 150);
            bitmap.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
            bitmap.draw(batch, "Power: " + power, Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 20);
        } else {
            bitmap.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);

            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                score = 0;
                power = 3;
                posX = 0;
                posY = 0;
                gameover = false;
            }
        }

        batch.end();
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
        for (Iterator<Rectangle> iter = enemies.iterator(); iter.hasNext(); ) {
            Rectangle enemy = iter.next();
            if (collide(enemy.x, enemy.y, enemy.width, enemy.height, xMissile, yMissile, missile.getWidth(), missile.getHeight()) && attack) {
                iter.remove();
                enemies.clear();
                attack = false;
                score++;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        spawnEnemies();
                    }
                }, 1);


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
                yMissile += 20;
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
        enemies.clear();

        float enemySpacing = Gdx.graphics.getWidth() / 4.0f;
        float enemyWidth = tEnemy.getWidth() * 1.5f;
        float enemyHeight = tEnemy.getHeight() * 1.5f;

        for (int i = 0; i < currentQuestion.answers.length; i++) {
            float enemyX = enemySpacing * (i + 1) - enemyWidth / 2;
            float enemyY = Gdx.graphics.getHeight() - enemyHeight;

            Rectangle enemy = new Rectangle(enemyX, enemyY, enemyWidth, enemyHeight);
            enemies.add(enemy);
        }
    }

    private boolean collide(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        return x1 + w1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2;
    }
}
