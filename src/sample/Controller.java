package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import java.util.Stack;

public class Controller {
    private final AStarGraph model;
    private final AStarFormView view;

    /* We setup the action for the buttons on the javaFX. For our euclideanCalculatorButton and manhattanCalculatorButton, we use it as a toggle
     * that changes if we need to use euclidean or manhattan calculation.
     * to show the shortest path with the PrintButton, we run our runAstar, when clicked. It should be noted, that the
     * PrintButton only works, if both the way of calculation, start and end vertex has been chosen. */
    public Controller(AStarGraph GraphModel, AStarFormView aStarFormView) {
        this.model = GraphModel;
        this.view = aStarFormView;
        view.euclideanCalculatorButton.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(), view.endVertexComB.getValue(),true));
        view.manhattanCalculatorButton.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(), view.endVertexComB.getValue(),false));
        EventHandler<ActionEvent> PrintRequestHndl = e -> runAstar(view.endVertexComB.getValue(), view.shortestpathTA);
        view.PrintButton.setOnAction(PrintRequestHndl);
    }

    /* Our runAstar prints the shortest distances from one vertex to another, and prints all our the Vertex it passes through */
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