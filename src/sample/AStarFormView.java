package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class AStarFormView {

    private GridPane Startview;
    private AStarGraph model;

    Button exitBtn = new Button("Exit");
    Label StartVertexLbl =new Label("Select start vertex:");
    ComboBox<Vertex> startVertexComB= new ComboBox<>();
    Button DijkstraBtn = new Button("Run A* from selected start");
    Label EndVertexLbl =new Label("Select destination:");
    ComboBox<Vertex> endVertexComB= new ComboBox<>();
    Button PrintBtn = new Button("Print shortest path");
    TextArea shortestpathTA=new TextArea();


    public AStarFormView(AStarGraph GraphModel){
        this.model= GraphModel;

        Startview = new GridPane();
        Startview.setMinSize(300,200);
        Startview.setPadding(new Insets(10,10,10,10));
        Startview.setVgap(5);
        Startview.setHgap(1);

        ObservableList<Vertex> VertexList = FXCollections.observableArrayList(model.getVertices());
        Callback<ListView<Vertex>, ListCell<Vertex>> VertexcellFactory = new Callback<>() {

            @Override
            public ListCell<Vertex> call(ListView<Vertex> vertexListView) {
                return new ListCell<Vertex>(){
                    @Override
                    protected void updateItem(Vertex vertex, boolean empty) {
                        super.updateItem(vertex, empty);// to call method from parent class
                        if ( vertex ==null || empty){
                            setText(null);
                        }
                        else{
                            setText(vertex.getid());
                        }
                    }
                };
            }
        };


        startVertexComB.setItems(VertexList);
        startVertexComB.setButtonCell(VertexcellFactory.call(null));
        startVertexComB.setCellFactory(VertexcellFactory);
        startVertexComB.setValue(model.getVertices().get(0));
        endVertexComB.setItems(VertexList);
        endVertexComB.setButtonCell(VertexcellFactory.call(null));
        endVertexComB.setCellFactory(VertexcellFactory);
        endVertexComB.setValue(model.getVertices().get(0));
        shortestpathTA.setPrefColumnCount(1);

        //Add controls to pane
        Startview.add(StartVertexLbl, 1,1);
        Startview.add(startVertexComB,15,1);
        Startview.add(DijkstraBtn,15,2);
        Startview.add(EndVertexLbl,1,3);
        Startview.add(endVertexComB,15,3);
        Startview.add(PrintBtn,15,4);
        Startview.add(shortestpathTA,15,5);
        Startview.add(exitBtn,20,6);
    }
    public Parent asParent(){
        return Startview;
    }
}