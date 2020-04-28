package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import java.util.Stack;

public class Controller {
    private final AStarGraph model;
    private final AStarFormView view;

    public Controller(AStarGraph GraphModel, AStarFormView aStarFormView) {
        this.model = GraphModel;
        this.view = aStarFormView;
        view.algorithmButton.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(), view.endVertexComB.getValue(),true));
        view.algorithmeButtonM.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(), view.endVertexComB.getValue(),false));
        EventHandler<ActionEvent> PrintRequestHndl = e -> runAstar(view.endVertexComB.getValue(), view.shortestpathTA);
        view.PrintButton.setOnAction(PrintRequestHndl);
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
