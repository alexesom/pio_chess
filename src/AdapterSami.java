import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdapterSami extends MouseAdapter {
    private JLayeredPane myLayeredPane;
    private JPanel clickedPanel;
    private JPanel disappearPanel;
    private JPanel testPanel;
    private Point offset;
    private Point clickPoint;
    boolean isCatched = false;
    private Point destinationPoint = new Point();

    public AdapterSami(JLayeredPane layer) {
        myLayeredPane = layer;
    }

    @Override
    public void mousePressed (MouseEvent e){
        clickPoint = e.getPoint(); // klikasz myszką i bierze to ten punkt
        if (clickedPanel != null) { // jezeli to jest miejsce bez panela i panel jest zaznaczony to :
            try {
                disappearPanel = (JPanel) myLayeredPane.findComponentAt(clickPoint);

            } catch (ClassCastException ex) {
                isCatched = true;

                clickPoint.x = e.getX() / 70 * 70 + 10;
                clickPoint.y = e.getY() / 70 * 70 + 10;
                moveSelectedPanelTo(clickPoint); // przesun panel do tego pukntu

            }

            if(isCatched == false)
            {
                clickedPanel.setLocation(disappearPanel.getX(), disappearPanel.getY());
                disappearPanel.setLocation(500, 500);
                disappearPanel.removeMouseListener(this);


            }
            offset = null;
            clickedPanel = null; // ustaw ze teraz bedzie wybierac ponownie panel do przesunięcia
            clickPoint = null;  // i punkt w który chcesz przesunąć też
            isCatched = false;
        }
         else {
            try {
                clickedPanel = (JPanel) myLayeredPane.getComponentAt(e.getPoint());  //
            } catch (ClassCastException exception) {
                System.out.println("TY DZBANIE!!!!");
            }
        }
    }

    @Override
    public void mouseReleased (MouseEvent e){
        if (!e.getPoint().equals(clickPoint)) {
            clickedPanel = null;
        }
        clickPoint = null;
    }

    private void moveSelectedPanelTo (Point p){
        if (clickedPanel != null) {
            int x = p.x;
            int y = p.y;
            System.out.println(x + "x" + y);
            clickedPanel.setLocation(x, y);
        }
    }
}