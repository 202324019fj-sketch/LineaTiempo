/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lineatiempo;

/**
 *
 * @author Admin
 */

//Agregar librerias
import javax.swing.*; // Para la interfaz grafica
import java.awt.*; // Para dibujar 
import java.util.*; // Para listas y estructuras de datos


public class LineaTiempo extends JFrame {

    public LineaTiempo() {
        // Titulo de la ventana
        setTitle("Línea de Tiempo de Sistemas Operativos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500); // Tamaño de ventana
        // Centrar la ventana
        setLocationRelativeTo(null);
        
        // Lista de eventos (fecha + suceso)
        java.util.List<Evento> eventos = Arrays.asList(
            new Evento("1940", "Se accedia directamente a la consola donde se actuaba sobre una serie de micro interruptores."), 
            new Evento("1950", "Los sistemas operativos hacen una aparicion discreta y bastante simple."),
            new Evento("1970", "La universidad de Stanford inauguro su servicio de Red de Internet."),
            new Evento("1976", "AT&T desarrollo un protocolo de comunicacion con el sistema operativo UNIX."),
            new Evento("1980", "Se crea este sistema operativo, pero hasta la salida de Windows 95 se le considera un sistema operativo."),
            new Evento("1981", "Microsoft compro un sistema operativo: MS-DOS."),
            new Evento("1984", "El lanzamiento oficial del ordenador que incluia Mac OS."),
            new Evento("1990", "Este sistema es una versión mejorada de Unix. Al principio trabajaba en comandos."),
            new Evento("1976-2010", "Windows 98, XP, Windows Milenio y años despues Windows 2000, 2007."),
            new Evento("2013", "Google presenta una nueva version de su sistema operativo Android y sale Windows 8.")
        );

        // Panel que dibuja la linea de tiempo
        TimelinePanel panel = new TimelinePanel(eventos);

        // JScrollPane permite mover la linea en la ventana
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // Se agrega el scroll con el panel al JFrame
        add(scroll);
    }

    
    // Metodo principal
    public static void main(String[] args) {
        //invokeLater asegura que la interfaz se ejecute de forma correcta 
        SwingUtilities.invokeLater(() -> new LineaTiempo().setVisible(true)); //setVisible(true) muestra la ventana
    }
}


// Clase para guardar cada evento
class Evento {
    String fecha;
    String suceso;
    String rutaImagen;

    public Evento(String fecha, String suceso) {
        this.fecha = fecha;
        this.suceso = suceso;
        this.rutaImagen = rutaImagen;
    }
}


// Panel que dibuja la linea de tiempo
class TimelinePanel extends JPanel {
    private java.util.List<Evento> eventos;

    public TimelinePanel(java.util.List<Evento> eventos) {
        this.eventos = eventos;
        //Define el tamaño del panel
        setPreferredSize(new Dimension(200 * eventos.size(), 400));
        //Cambia el color de fondo
        setBackground(new Color(220, 235, 250)); 
    }

   
    @Override
    protected void paintComponent(Graphics g) {
        // Limpiar fondo
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int h = getHeight();
        // Línea en el centro
        int y = h / 2; 
        // Margen izquierdo
        int margin = 60;
        //Distancia entre eventos
        int espacio = 180; 

        // Dibujar la linea central
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(margin, y, margin + (eventos.size() - 1) * espacio, y);

        // Dibujar cada evento
        for (int i = 0; i < eventos.size(); i++) {
            Evento ev = eventos.get(i);
            int x = margin + i * espacio;

            // Punto en la linea
            g2.setColor(Color.RED);
            g2.fillOval(x - 5, y - 5, 10, 10);

            g2.setColor(Color.BLACK);
            // Fecha arriba
            g2.drawString(ev.fecha, x - 20, y - 20);

            // Texto del suceso abajo (multilínea)
            drawStringMultiline(g2, ev.suceso, x - 80, y + 30, 160);
        }  
        

    }

    // Metodo para dividir el texto largo en varias lineas
    private void drawStringMultiline(Graphics2D g2, String text, int x, int y, int maxWidth) {
        //Mide el texto
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        String line = "";
        int lineHeight = fm.getHeight();

        for (String word : words) {
            //Divide el texto en varias lineas si se pasa del ancho maximo (maxWidth)
            if (fm.stringWidth(line + word) > maxWidth) {
                g2.drawString(line, x, y);
                y += lineHeight;
                line = word + " ";
            } else {
                line += word + " ";
            }
        }
        // Se crea la ultima lnea
        g2.drawString(line, x, y);
    }
}

