package metaheurystyczne;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Rysowanie extends JPanel{
	
	MacierzEuc macierz;
	Rozwiazanie rozwiazanie;
	
	public Rysowanie(MacierzEuc macierz, Rozwiazanie rozwiazanie) {
		
		this.macierz = macierz;
		this.rozwiazanie = rozwiazanie;
		
		double max = 0;
		
		for(int i=0; i<=macierz.rozmiar-1; i++) {
			
			if(macierz.x_coordinates[i] > max) {
				max = macierz.x_coordinates[i];
			}
			
			if(macierz.y_coordinates[i] > max) {
				max = macierz.y_coordinates[i];
			}
			
		}
		
	
		for(int i=0; i<=macierz.rozmiar-1; i++) {
				
			macierz.x_coordinates[i] = macierz.x_coordinates[i]*1000.0/max;
			macierz.y_coordinates[i] = macierz.y_coordinates[i]*1000.0/max;
				
		}	
		
		
		JFrame okno = new JFrame();
		
		okno.setBounds(500, 10, 1000, 1000);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		okno.add(this);
		okno.setVisible(true);
		
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLACK);
		
		float x, y, x1, y1, x2, y2;
		
		for(int i=0; i<=macierz.rozmiar-1; i++) {
			
			x = (float)macierz.x_coordinates[i];
			y = (float)macierz.y_coordinates[i];
			
			Ellipse2D ellipse = new Ellipse2D.Float(x, y, 7, 7);
			g2d.fill(ellipse);
			repaint();
		}
		
		g2d.setColor(Color.RED);
		
		if(rozwiazanie == null) {
			return;
		}
		
		for(int i=0; i<=rozwiazanie.rozmiar-1; i++) {
			
			x1 = (float)macierz.x_coordinates[rozwiazanie.wierzcholki[i]-1];
			y1 = (float)macierz.y_coordinates[rozwiazanie.wierzcholki[i]-1];
			
			x2 = (float)macierz.x_coordinates[rozwiazanie.wierzcholki[(i+1)%rozwiazanie.rozmiar]-1];
			y2 = (float)macierz.y_coordinates[rozwiazanie.wierzcholki[(i+1)%rozwiazanie.rozmiar]-1];
			
			Line2D line = new Line2D.Float(x1, y1, x2, y2);
			g2d.draw(line);
			repaint();
		}
		
		
	}
	
    @Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        doDrawing(g);
    }
	
	
}
