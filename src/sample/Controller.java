package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.Stack;

public class Controller {
    private final AStarGraph model;
    private final AStarFormView view;

    public Controller(AStarGraph GraphModel, AStarFormView aStarFormView) {
        this.model = GraphModel;
        this.view = aStarFormView;
        view.exitBtn.setOnAction(e -> Platform.exit());
        view.DijkstraBtn.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(), view.endVertexComB.getValue()));
        EventHandler<ActionEvent> PrintRequestHndl = e -> runAstar(view.endVertexComB.getValue(), view.shortestpathTA);
        view.PrintBtn.setOnAction(PrintRequestHndl);
    }
        @FXML
        public void initialize () {


        }

        public void runAstar (Vertex destination, TextArea TArea){
            Vertex pvertex = destination;
            TArea.appendText("To " + destination.getid() + " Shortest length: \n");
            Stack<Vertex> Path = new Stack<>();
            int limit =0;
            while (pvertex!=null) {
                Path.push(pvertex);
                pvertex = pvertex.getPrev();
            }
                if (!Path.isEmpty()) {
                    limit = Path.size();
                }
                for (int i=0; i<limit -1; i++) {
                    TArea.appendText(Path.pop().getid() + " -> \n");
                }
                if (limit >0) {
                    TArea.appendText(Path.pop().getid() + "\n");
                }
        }
}
