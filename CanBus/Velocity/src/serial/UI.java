package serial;


import java.util.Random;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class UI extends Application {
    private static final Random         RND            = new Random();
    private static final double         MIN_CELL_SIZE  = 100;
    private static final double         PREF_CELL_SIZE = 400;
    private static final double         MAX_CELL_SIZE  = 50;
    private static       int            noOfNodes      = 0;

    private              Gauge          gauge8;


    private              long           lastTimerCall;
    private              AnimationTimer timer;
    static SerialReadWrite sw= new SerialReadWrite();
    

    @Override public void init() {

    	
        
        try {
			sw = new SerialReadWrite("COM12");
			//sc.consoleStart();
		} catch (Exception e) {
		    System.out.println("Connect Fail !");
			e.printStackTrace();
		}
       
        
        //UI ui = new UI();
  
        
//        new Thread(new Runnable(){
//            
//            @Override
//            public void run() {
//               while(true){
//                 
//                  if(sw.getVelocity() !=null){
//                     System.out.println("sendback to ECU");
//                     sw.send(sw.getVelocity());
//                
//                    sw.setSerialThreadControl(false);    
//                  }
//                  try {
//                    Thread.sleep(500);
//                 } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                 }
//                  continue;
//                 
//               }
//               
//            }
//         }).start();
        
        gauge8 = GaugeBuilder.create()
                             .skinType(SkinType.MODERN)
                             .prefSize(800, 800)
                             .sections(new Section(85, 90, "", Color.rgb(204, 0, 0, 0.5)),
                                       new Section(90, 95, "", Color.rgb(204, 0, 0, 0.75)),
                                       new Section(95, 100, "", Color.rgb(204, 0, 0)))
                             .sectionTextVisible(true)
                             .title("Velocity")
                             .unit("KM")
                             .threshold(120)
                             .thresholdVisible(true)
                             .animated(true)
                             .build();
        gauge8.setMaxValue(160);

        
       

        //framedGauge1 = new FGauge(gauge1, GaugeDesign.ENZO, GaugeBackground.DARK_GRAY);
        //framedGauge2 = new FGauge(gauge2, GaugeDesign.METAL);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
        	
            @Override public void handle(long now) {
            	if (now > lastTimerCall + 300_000_000l) {
                    
                    try{
                    	 if(sw.getVelocity()!=null) {
                    		 gauge8.setValue(Double.parseDouble(sw.getVelocity()));
                    		 
                         }else {
                         	gauge8.setValue(0);
                         	
                         }
                    	
                    }catch(Exception e) {
                    	e.printStackTrace();
                    }
                   
                    
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();

        pane.add(gauge8, 1, 1);
       

       
        pane.setHgap(1);
        pane.setVgap(1);
        pane.setPadding(new Insets(1));
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

