package com.inventorydvdlogo;

public class DVDLogo {
    double x;
    double y;
    double speed = 2.0;
    double vectorX = .7071;
    double vectorY = .7071;

    DVDLogo() {
        this.x = 500;
        this.y = 500;
    }

    public void travel() {
        this.x +=vectorX * speed;
        this.y += vectorY * speed;
    }

    public void flip(boolean topWall) {
        if (topWall)
            vectorY *= -1;
        else
            vectorX *= -1;
    }
}
