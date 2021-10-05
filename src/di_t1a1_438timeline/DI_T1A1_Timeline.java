/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t1a1_438timeline;

import com.sun.javafx.perf.PerformanceTracker;
import static java.lang.Math.random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author usuario
 */
public class DI_T1A1_Timeline extends Application {

    //Atributos
    private Group root;
    private Scene scene;
    private Circle circulo;
    private Label lbFPS;
    private int circuloSpeedX = 1;
    private int circuloSpeedY = 1;

    @Override
    public void start(Stage primaryStage) {

        //Creamos el layout de la escena que añadiremos en la primaryStage
        root = new Group();

        //Creamos la escena con el layout definido y sus dimensiones
        scene = new Scene(root, 300, 250);

        //Creamos el circulo que irá dando vueltas en la ventana
        circulo = new Circle(10);
        //Rellenamos el circulo con color negro
        circulo.setFill(Color.BLACK);
        //Posicionamos el circulo
        circulo.setTranslateX(300 * 0.5);
        circulo.setTranslateY(250 * 0.5);
        //Añadimos el circulo al root
        root.getChildren().add(circulo);

        //Creamos la etiqueta que servirá para los FPS
        lbFPS = new Label();
        //Posicionamos la Label
        lbFPS.setTranslateX(10);
        lbFPS.setTranslateY(10);
        //Añadimos lbFPS al root
        root.getChildren().add(lbFPS);

        //Incluimos la animacion
        EventHandler<ActionEvent> eH = e -> {
            //Mostramos los FPS
            PerformanceTracker ptFPS = PerformanceTracker.getSceneTracker(scene);
            lbFPS.setText("FPS (Timeline) = " + ptFPS.getInstantFPS());

            //Cambia la dirección de la bola si llega a los extremos
            if (circulo.getTranslateX() < 0 || circulo.getTranslateX() > 300) {
                circuloSpeedX *= -1;
            } else if (circulo.getTranslateY() < 0 || circulo.getTranslateY() > 250) {
                circuloSpeedY *= -1;

            } else {
                circuloSpeedX = 1;
                circuloSpeedY = 1;
                circuloSpeedX *= ((int) (Math.random() * 3)) - 1;
                circuloSpeedY *= ((int) (Math.random() * 3)) - 1;
            }

            //Movemos el circulo
            circulo.setTranslateX(circulo.getTranslateX() + circuloSpeedX);
            circulo.setTranslateY(circulo.getTranslateY() + circuloSpeedY);
        };

        //Definimos el bucle con la duración
        Timeline animacion = new Timeline(new KeyFrame(Duration.millis(5), eH));
        animacion.setCycleCount(Timeline.INDEFINITE);
        //Iniciamos l animación
        animacion.play();

        //Escogemos el titulo de nuestra ventana
        primaryStage.setTitle("Ejercicio 4.3.8 Timeline");
        //Escogemos la escena de nuestra ventana
        primaryStage.setScene(scene);
        //Mostramos la escena
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
