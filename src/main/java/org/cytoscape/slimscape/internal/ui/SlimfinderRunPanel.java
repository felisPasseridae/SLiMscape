package org.cytoscape.slimscape.internal.ui;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.slimscape.internal.RunSlimfinder;
import org.cytoscape.util.swing.OpenBrowser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.List;

/*
 * @author: Kevin O'Brien
 */
public class SlimfinderRunPanel extends JPanel {

    JComboBox comboBox = null;
    JComboBox attributeNameCombobox = null;
    JTextArea idTextArea = null;
    SlimfinderOptionsPanel optionsPanel;
    CyApplicationManager manager;
    List<String> input;

    public SlimfinderRunPanel(final CyApplicationManager manager, final OpenBrowser openBrowser,
                              final SlimfinderOptionsPanel optionsPanel) {
        setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{629, 0};
        gridBagLayout.rowHeights = new int[]{170, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);
        setPreferredSize(new Dimension(476, 167));

        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;

        JPanel sLiMFinderPanel = new JPanel();
        sLiMFinderPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder("Run SLiMFinder"),
                new EmptyBorder(0, 0, 0, 20)));
        GridBagConstraints gbc_sLiMFinderPanel = new GridBagConstraints();
        gbc_sLiMFinderPanel.fill = GridBagConstraints.BOTH;
        gbc_sLiMFinderPanel.gridx = 0;
        gbc_sLiMFinderPanel.gridy = 0;
        add(sLiMFinderPanel, gbc_sLiMFinderPanel);
        GridBagLayout gbl_sLiMFinderPanel = new GridBagLayout();
        gbl_sLiMFinderPanel.columnWidths = new int[]{497, 0};
        gbl_sLiMFinderPanel.rowHeights = new int[]{25, 87, 0};
        gbl_sLiMFinderPanel.columnWeights = new double[]{1.0,
                Double.MIN_VALUE};
        gbl_sLiMFinderPanel.rowWeights = new double[]{0.0, 0.0,
                Double.MIN_VALUE};
        sLiMFinderPanel.setLayout(gbl_sLiMFinderPanel);
        JButton runSLiMFinderButton = new JButton("RunSLiMFinder");
        GridBagConstraints gbc_runSLiMFinderButton = new GridBagConstraints();
        gbc_runSLiMFinderButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_runSLiMFinderButton.insets = new Insets(0, 0, 5, 0);
        gbc_runSLiMFinderButton.gridx = 0;
        gbc_runSLiMFinderButton.gridy = 0;
        sLiMFinderPanel.add(runSLiMFinderButton, gbc_runSLiMFinderButton);

        JPanel slimSearchOptionsPanel = new JPanel();
        slimSearchOptionsPanel.setBorder(new TitledBorder(new LineBorder(
                new Color(184, 207, 229)), "Parameters", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        GridBagConstraints gbc_slimSearchOptionsPanel = new GridBagConstraints();
        gbc_slimSearchOptionsPanel.insets = new Insets(0, 0, 5, 0);
        gbc_slimSearchOptionsPanel.fill = GridBagConstraints.BOTH;
        gbc_slimSearchOptionsPanel.gridx = 0;
        gbc_slimSearchOptionsPanel.gridy = 1;
        sLiMFinderPanel.add(slimSearchOptionsPanel, gbc_slimSearchOptionsPanel);

        GridBagLayout gbl_slimSearchOptionsPanel = new GridBagLayout();
        gbl_slimSearchOptionsPanel.columnWidths = new int[]{0, 0};
        gbl_slimSearchOptionsPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_slimSearchOptionsPanel.columnWeights = new double[]{1.0,
                Double.MIN_VALUE};
        gbl_slimSearchOptionsPanel.rowWeights = new double[]{1.0, 0.0, 1.0,
                Double.MIN_VALUE};
        slimSearchOptionsPanel.setLayout(gbl_slimSearchOptionsPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        slimSearchOptionsPanel.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel idLabel = new JLabel("Run ID:");
        GridBagConstraints gbc1_motifLabel = new GridBagConstraints();
        gbc1_motifLabel.anchor = GridBagConstraints.WEST;
        gbc1_motifLabel.insets = new Insets(0, 0, 5, 5);
        gbc1_motifLabel.gridx = 0;
        gbc1_motifLabel.gridy = 1;
        slimSearchOptionsPanel.add(idLabel, gbc1_motifLabel);

        idTextArea = new JTextArea();
        GridBagConstraints gbc1_textArea = new GridBagConstraints();
        gbc1_textArea.insets = new Insets(0, 0, 0, 5);
        gbc1_textArea.fill = GridBagConstraints.BOTH;
        gbc1_textArea.gridx = 0;
        gbc1_textArea.gridy = 2;
        slimSearchOptionsPanel.add(idTextArea, gbc1_textArea);

        runSLiMFinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CyNetwork network = manager.getCurrentNetwork();
                // There is a past runs ID in the box
                if (idTextArea.getText().length() > 6) {
                    // Send request to the server for that page
                    String id = idTextArea.getText();
                    try {
                        List<String> results = PrepareResults(
                                ("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=main"));
                        if (results == null) {
                            // TODO: Insert error catch here (?show page)
                        } else {
                            JScrollPane csv = createCsvTable(results);  // TODO: Work out how to add this to the panel.
                            //JTable occ = createResultTable((List<String>) results.get("occ"));
                            Container customPanel = new JPanel();
                            csv.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "CSV Output",
                                    TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
                            GridBagConstraints gbc_customPanel = new GridBagConstraints();
                            gbc_customPanel.fill = GridBagConstraints.BOTH;
                            gbc_customPanel.gridx = 0;
                            gbc_customPanel.gridy = 3;
                            add(customPanel, gbc_customPanel);

                            customPanel.add(csv, BorderLayout.CENTER);

                            // TODO: As above for occ
                            // List<String> results = PrepareResults(
                            //("http://rest.slimsuite.unsw.edu.au/retrieve&jobid=" + id + "&rest=occ"));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                } else {
                    List<CyNode> selected = new ArrayList<CyNode>();
                    selected.addAll(CyTableUtil.getNodesInState(network, "selected", true));
                    if (selected.size() > 0) {
                        new RunSlimfinder(network, selected, optionsPanel); // I want to get this URL back to process here.
                    } else {
                        JOptionPane.showMessageDialog(null, "No nodes selected!");
                    }
                }
            }
        });
    }

    /**
     * @desc attains and analyses main results from the Slimsuite server
     * @param url - The url where the results should be
     * @return Map - A map of List<String> containing the contents of the csv and occ blocks.
     *               If there is an error, null is returned.
     * @throws Exception
     */
    private List<String> PrepareResults(String url) {
        // Gets URL data
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            String inputLine;
            String lineOne = in.readLine(); // Reads in the first line

            // There is an error in the results obtained
            if (lineOne.startsWith("ERROR")) {
                in.close();
                return null;
            } else {
                List<String> csv = new ArrayList<String>();
                int separators = 0;

                while ((inputLine = in.readLine()) != null) {
                    boolean braces = false;
                    StringBuilder builder = new StringBuilder();
                    boolean inBraces = false;
                    for (char x : inputLine.toCharArray()) {
                        if (x == '{') {
                            inBraces = true;
                            builder.append(x);
                        } else if (x == '}') {
                            inBraces = false;
                            builder.append(x);
                        } else if (x == ',' && inBraces) {
                            builder.append('|');
                        } else {
                            builder.append(x);
                        }
                    }

                    String toReturn = builder.toString();

                    csv.add(toReturn);
                }
                in.close();

                return csv;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + " in function");
            return null;
        }
    }

    /**
     * @desc Creates a JTable from an input of tab separated strings
     * @param input - a List<String> consisting of a series of tab-separated lines
     * @return JTable - a table populated with the input elements
     */
    private JScrollPane createCsvTable (List<String> input) {
        // Get column names from input
        JTable table;
        List<String> names = Arrays.asList(input.get(2).split(","));
        List<String> abbreviated = names.subList(10, names.size()-5);  // TODO: get this to show
        Object columnNames[] = new String[abbreviated.size()]; // line 2
        abbreviated.toArray(columnNames);

        // Create table
        table = new JTable(new DefaultTableModel(null, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(400, 700));
        table.setFillsViewportHeight(true);

        // Add a row in table for each element in the input
        int lines = input.size();
        for(int c=3; c<lines; c++) {
            List<String> line = Arrays.asList(input.get(c).split(","));
            List<String> abbreviate = line.subList(10, line.size()-5);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 2) {
                column.setMinWidth(100);
            } else {
                column.setMinWidth(50);
            }
        }
        JScrollPane pane = new JScrollPane();
        pane.add(table);
        return pane;
    }

    private JTable createOccTable (List<String> input) {
        JTable table;
        List<String> names = Arrays.asList(input.get(2).split(","));
        List<String> abbreviated = names.subList(3, names.size()-7);
        Object columnNames[] = new String[abbreviated.size()]; // line 2
        abbreviated.toArray(columnNames);

        // Create table
        table = new JTable(new DefaultTableModel(null, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(400, 700));
        table.setFillsViewportHeight(true);

        // Add a row in table for each element in the input
        int lines = input.size();
        for(int c=3; c<lines; c++) {
            List<String> line = Arrays.asList(input.get(c).split(","));
            List<String> abbreviate = line.subList(3, line.size()-7);
            Object lineObject[] = new String[abbreviate.size()];
            abbreviate.toArray(lineObject);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(lineObject);
        }

        // Table formatting
        table.setEnabled(false);
        TableColumn column;
        for (int i = 0; i < abbreviated.size(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMinWidth(50);
        }

        return table;
    }
}