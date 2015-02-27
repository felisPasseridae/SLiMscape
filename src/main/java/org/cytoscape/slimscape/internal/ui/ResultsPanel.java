package org.cytoscape.slimscape.internal.ui;


import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {

    public ResultsPanel (JScrollPane csv, JScrollPane occ, JButton resultsButton) {
        csv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        csv.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        occ.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        occ.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill  = GridBagConstraints.BOTH;

        GridBagConstraints gbc_csv = new GridBagConstraints();
        gbc_csv.gridx = 0;
        gbc_csv.gridy = 0;
        gbc_csv.weightx = 1;
        gbc_csv.weighty = 1;
        gbc_csv.fill  = GridBagConstraints.BOTH;
        add(csv, gbc_csv);

        GridBagConstraints gbc_occ = new GridBagConstraints();
        gbc_occ.gridx = 0;
        gbc_occ.gridy = 1;
        gbc_occ.weightx = 1;
        gbc_occ.weighty = 1;
        gbc_occ.fill  = GridBagConstraints.BOTH;
        add(occ, gbc_occ);

        GridBagConstraints gbc_results = new GridBagConstraints();
        gbc_results.gridx = 0;
        gbc_results.gridy = 2;
        gbc_results.weightx = 1;
        gbc_results.weighty = 0;
        gbc_results.fill  = GridBagConstraints.BOTH;
        JPanel resultsButtonPanel = new JPanel();
        resultsButtonPanel.add(resultsButton);
        add(resultsButtonPanel, gbc_results);
    }

}