package serial;


import java.time.Clock;
import java.time.LocalTime;
import java.util.Random;

import eu.hansolo.medusa.Clock.ClockSkinType;
import eu.hansolo.medusa.ClockBuilder;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.ScaleDirection;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.LcdDesign;
import eu.hansolo.medusa.LcdFont;
import eu.hansolo.medusa.Marker;
import eu.hansolo.medusa.Marker.MarkerType;
import eu.hansolo.medusa.Section;
import eu.hansolo.medusa.SectionBuilder;
import eu.hansolo.medusa.TickLabelLocation;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.TickMarkType;
import eu.hansolo.medusa.TimeSection;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import serial.SerialReadWrite.Serialwrite;


public class UI extends Application {
	private static final Random         RND            = new Random();
    private static final double         MIN_CELL_SIZE  = 80;
    private static final double         PREF_CELL_SIZE = 120;
    private static final double         MAX_CELL_SIZE  = 150;
    private static       int            noOfNodes      = 0;
    private static       int i = 100;
    
    private              Gauge          gauge29;
  
    private              long           lastTimerCall;
    private              AnimationTimer timer;

    static SerialReadWrite sw= new SerialReadWrite();
    
    @Override public void init() {
    	
    	
    	try {
			sw = new SerialReadWrite("COM12");
		
		} catch (Exception e) {
		    System.out.println("Connect Fail !");
			e.printStackTrace();
		}

        gauge29 = GaugeBuilder.create()
                              .skinType(SkinType.TILE_KPI)
                              .threshold(75)
                              .animated(true)
                              .value(100)
                              .build();

     

        //framedGauge1 = new FGauge(gauge1, GaugeDesign.ENZO, GaugeBackground.DARK_GRAY);
        //framedGauge2 = new FGauge(gauge2, GaugeDesign.METAL);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 3_000_000_000l) {
                	gauge29.setValue(i);
                	int a = (int)(Math.random()*5+1);
                	 
                	sw.send(i+"");
                	
					i = i-a;              		
                    if (i<0) {
                    	gauge29.setValue(0);
                    }
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();

        pane.add(gauge29, 1, 1);

      
      
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));
      
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(90, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);

        stage.setTitle("Medusa Gauges and Clocks");
        stage.setScene(scene);
        stage.show();

        timer.start();

        // Calculate number of nodes
        calcNoOfNodes(pane);
        System.out.println(noOfNodes + " Nodes in SceneGraph");
    }

    @Override public void stop() {
        System.exit(0);
    }


    // ******************** Misc **********************************************
    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) { calcNoOfNodes(n); }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
