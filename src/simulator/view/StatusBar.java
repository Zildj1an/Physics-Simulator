package simulator.view;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusBar extends JPanel implements SimulatorObserver {

    private static final String TEXT_TIME = "Time:";
    private static final String TEXT_LAW = "Laws:";
    private static final String TEXT_BODIES = "Bodies:";

    private Controller ctrl;

    private JLabel currTime;
    private JLabel currLaws;
    private JLabel numOfBodies;
    JSeparator separator;


    StatusBar(Controller ctrl){
        initGUI();
        this.ctrl = ctrl;
        this.ctrl.addObserver(this);
    }

    private void initGUI(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder( BorderFactory.createBevelBorder( 1 ));



        currTime = new JLabel(TEXT_TIME);
        currLaws = new JLabel(TEXT_LAW);
        numOfBodies = new JLabel(TEXT_BODIES);

        currTime.setPreferredSize(new Dimension(120, 20));
        currLaws.setPreferredSize(new Dimension(270, 20));
        numOfBodies.setPreferredSize(new Dimension(100, 20));
        add(currTime);
        add(createSeaparator());
        add(numOfBodies);
        add(createSeaparator());
        add(currLaws);
    }

    private JSeparator createSeaparator(){
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(1,20));
        separator.setBackground(Color.GRAY.brighter());
        separator.setForeground(Color.GRAY.brighter());

        return separator;
    }


    @Override
    public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
        numOfBodies.setText(TEXT_BODIES + " " + bodies.size());
        currTime.setText(TEXT_TIME + " " + time);
        currLaws.setText(TEXT_LAW + " " + gLawsDesc);
    }

    @Override
    public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
        numOfBodies.setText(TEXT_BODIES + " " + bodies.size());
        currTime.setText(TEXT_TIME + " " + time);
        currLaws.setText(TEXT_LAW + " " + gLawsDesc);
    }

    @Override
    public void onBodyAdded(List<Body> bodies, Body b) {
        numOfBodies.setText(TEXT_BODIES + " " + bodies.size());
    }

    @Override
    public void onAdvance(List<Body> bodies, double time) {
        currTime.setText(TEXT_TIME + " " + time);
    }

    @Override
    public void onDeltaTimeChanged(double dt) {

    }

    @Override
    public void onGravityLawChanged(String gLawsDesc) {
        currLaws.setText(TEXT_LAW + " " + gLawsDesc);
    }
}
