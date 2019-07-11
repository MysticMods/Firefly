package mart.firefly.gui.button;

import mart.firefly.gui.BaseContainer;

public class ContainerButton {

    public final int x, y, x2, y2;

    public ContainerButton(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isMouseOn(double mouseX, double mouseY){
        return mouseX > x && mouseX < x2 && mouseY > y && mouseY < y2;
    }


    public void activate(BaseContainer container){

    }

}
