import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

public class Admin implements ActionListener {
  JButton addFlightButton;
  JButton viewBookingButton;
  JButton viewFlightsButton;
  JButton removeButton;
  JButton searchButton;
  JButton menuButton;
  JButton exitButton;
  JPanel panel;
  JLabel label;

  JFrame userFrame = new JFrame("Airline Reservation System");
  JFrame searchFrame = new JFrame("Airline Reservation System");
  JFrame bookingFrame = new JFrame("Airline Reservation System");
  JFrame flightsFrame = new JFrame("Airline Reservation System");

  File bookingsFile = new File(
    "D:/Java/AirlineReservationSystem/src/Bookings.txt"
  );
  File flightsFile = new File(
    "D:/Java/AirlineReservationSystem/src/Flights.txt"
  );

  String[] bookings = new String[100];
  String[] flights = new String[100];
  String[] searchResults = new String[100];
  JList bookingList = new JList();
  JList flightList = new JList();
  JList searchList = new JList();
  String flightNo = "";
  String from = "";
  String to = "";
  String time = "";
  String date = "";
  String flight = "";
  String searchKey = "";
  boolean removeFlight = false;
  boolean removeBooking = false;
  String option = "";

  public void mainWindow() throws IOException {
    userFrame = new JFrame("Airline Reservation System");
    userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    userFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panel = new JPanel();
    panel.setBackground(new Color(58, 58, 60));
    userFrame.add(panel);
    panel.setLayout(null);

    label = new JLabel("Airline Reservation System");
    label.setBounds(400, 100, 840, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.BOLD, 50));
    panel.add(label);

    label = new JLabel("Admin Dashboard");
    label.setBounds(540, 225, 270, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.BOLD, 30));
    panel.add(label);

    addFlightButton = new JButton("Add Flight");
    addFlightButton.addActionListener(this);
    addFlightButton.setBounds(220, 350, 200, 100);
    addFlightButton.setFont(new Font("Serif", Font.BOLD, 25));
    addFlightButton.setBackground(new java.awt.Color(238, 186, 76));
    addFlightButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(addFlightButton);

    viewBookingButton = new JButton("Bookings");
    viewBookingButton.addActionListener(this);
    viewBookingButton.setBounds(460, 350, 200, 100);
    viewBookingButton.setFont(new Font("Serif", Font.BOLD, 25));
    viewBookingButton.setBackground(new java.awt.Color(238, 186, 76));
    viewBookingButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(viewBookingButton);

    searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    searchButton.setBounds(700, 350, 200, 100);
    searchButton.setFont(new Font("Serif", Font.BOLD, 25));
    searchButton.setBackground(new java.awt.Color(238, 186, 76));
    searchButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(searchButton);

    viewFlightsButton = new JButton("Added Flights");
    viewFlightsButton.addActionListener(this);
    viewFlightsButton.setBounds(940, 350, 200, 100);
    viewFlightsButton.setFont(new Font("Serif", Font.BOLD, 25));
    viewFlightsButton.setBackground(new java.awt.Color(238, 186, 76));
    viewFlightsButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(viewFlightsButton);

    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    exitButton.setBounds(575, 550, 200, 75);
    exitButton.setFont(new Font("Serif", Font.BOLD, 25));
    exitButton.setBackground(new java.awt.Color(238, 186, 76));
    exitButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(exitButton);

    userFrame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addFlightButton) {
      try {
        viewFlights();
        getFlightInfo();
        addFlight();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource() == viewBookingButton) {
      try {
        viewBookings();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource() == searchButton) {
      option =
        JOptionPane.showInputDialog(
          null,
          "Press 1 to search Bookings\nPress 2 to Search Flights"
        );
      try {
        if (option == null) {
          JOptionPane.showMessageDialog(null, "Invalid input");
          return;
        } else if (option.isBlank()) {
          JOptionPane.showMessageDialog(null, "Invalid input");
          return;
        } else {
          if (option.equals("1")) {
            searchKey =
              JOptionPane.showInputDialog(null, "Enter Anything to Search");
            if (searchKey.isBlank()) {
              JOptionPane.showMessageDialog(null, "Invalid input");
            } else {
              searchFrame.dispose();
              searchBooking(searchKey);
            }
          } else if (option.equals("2")) {
            if (searchKey == null) {
              JOptionPane.showMessageDialog(null, "Invalid input");
              return;
            } else if (searchKey.isBlank()) {
              JOptionPane.showMessageDialog(null, "Invalid input");
              return;
            } else {
              searchKey =
                JOptionPane.showInputDialog(null, "Enter Anything to Search");
              if (searchKey.isBlank()) {
                JOptionPane.showMessageDialog(null, "Invalid input");
              } else {
                searchFrame.dispose();
                searchFlight(searchKey);
              }
            }
          } else {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
          }
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource() == viewFlightsButton) {
      try {
        viewFlights();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource() == removeButton) {
      String s = "";
      if (!removeBooking && removeFlight) {
        if (flightList.getSelectedValue() == null) {
          JOptionPane.showMessageDialog(null, "Select item to remove");
        } else {
          try {
            s = (String) flightList.getSelectedValue();
            remove(flightsFile, flights, s);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }
      } else if (removeBooking && !removeFlight) {
        if (bookingList.getSelectedValue() == null) {
          JOptionPane.showMessageDialog(null, "Select item to remove");
        } else {
          try {
            s = (String) bookingList.getSelectedValue();
            remove(bookingsFile, bookings, s);
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }
      }
    } else if (e.getSource() == exitButton) {
      System.exit(0);
    }
  }

  public void getFlightInfo() throws IOException {
    flightNo = JOptionPane.showInputDialog("Enter Flight Number");
    from = JOptionPane.showInputDialog("From");
    to = JOptionPane.showInputDialog("To");
    time = JOptionPane.showInputDialog("Enter Flight Time");
    date = JOptionPane.showInputDialog("Enter Date");
  }

  public void addFlight() throws IOException {
    if (
      flightNo == null ||
      from == null ||
      to == null ||
      time == null ||
      date == null
    ) {
      JOptionPane.showMessageDialog(null, "Invalid inputs");
      return;
    } else if (
      flightNo.isBlank() ||
      from.isBlank() ||
      to.isBlank() ||
      time.isBlank() ||
      date.isBlank()
    ) {
      JOptionPane.showMessageDialog(null, "Invalid inputs");
      return;
    } else {
      FileWriter writer = new FileWriter(flightsFile, true);
      writer.write(
        flightNo +
        " - " +
        from +
        " - " +
        to +
        " - " +
        time +
        " - " +
        date +
        "\n"
      );
      writer.close();
      viewFlights();
      JOptionPane.showMessageDialog(null, "Flight Added");
    }
  }

  public void viewFlights() throws FileNotFoundException {
    removeFlight = true;
    removeBooking = false;
    flightsFrame = new JFrame("Airline Reservation System");
    flightsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    flightsFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panel = new JPanel();
    panel.setBackground(new Color(58, 58, 60));
    flightsFrame.add(panel);
    panel.setLayout(null);

    label = new JLabel("Added Flights");
    label.setBounds(550, 10, 225, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.BOLD, 35));
    panel.add(label);

    removeButton = new JButton("Remove");
    removeButton.addActionListener(this);
    removeButton.setBounds(575, 625, 200, 50);
    removeButton.setFont(new Font("Serif", Font.BOLD, 25));
    removeButton.setBackground(new java.awt.Color(238, 186, 76));
    removeButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(removeButton);

    menuButton = new JButton("Back");
    menuButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent event) {
          flightsFrame.dispose();
        }
      }
    );
    menuButton.setBounds(40, 40, 150, 50);
    menuButton.setFont(new Font("Serif", Font.BOLD, 25));
    menuButton.setBackground(new java.awt.Color(238, 186, 76));
    menuButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(menuButton);

    Scanner reader = new Scanner(flightsFile);
    flights = new String[100];
    int i = 0;
    String entry = "";
    while (reader.hasNextLine()) {
      entry = reader.nextLine();
      flights[i] = entry;
      i++;
    }
    reader.close();

    flightList = new JList(flights);
    flightList.setFont(new Font("Serif", Font.BOLD, 20));
    flightList.setBounds(75, 140, 1200, 450);
    flightList.setFixedCellWidth(50);
    flightList.setFixedCellHeight(50);
    flightList.setOpaque(true);
    panel.add(flightList);
    flightsFrame.setVisible(true);
  }

  public void remove(File file, String[] array, String s) throws IOException {
    PrintWriter pw = new PrintWriter(file);
    pw.print("");
    pw.close();
    FileWriter writer = new FileWriter(file, true);
    int j = 0;
    while (j <= array.length) {
      if (array[j] == s) {
        j++;
      } else if (array[j] == null) {
        break;
      } else {
        writer.write((String) array[j] + "\n");
        j++;
      }
    }
    writer.close();

    if (file.getName().equals("Bookings.txt")) {
      Scanner reader = new Scanner(bookingsFile);
      bookings = new String[100];
      int i = 0;
      while (reader.hasNextLine()) {
        String str = reader.nextLine();
        if (str != "") {
          bookings[i] = str;
          i++;
        } else {
          break;
        }
      }
      bookingList.setListData(bookings);
    } else if (file.getName().equals("Flights.txt")) {
      Scanner reader = new Scanner(flightsFile);
      flights = new String[100];
      int i = 0;
      while (reader.hasNextLine()) {
        String str = reader.nextLine();
        if (str != "") {
          flights[i] = str;
          i++;
        } else {
          break;
        }
      }
      flightList.setListData(flights);
    } else {
      JOptionPane.showMessageDialog(null, "File Not Found");
    }
  }

  public void viewBookings() throws FileNotFoundException {
    removeFlight = false;
    removeBooking = true;
    bookingFrame = new JFrame("Airline Reservation System");
    bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    bookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panel = new JPanel();
    bookingFrame.add(panel);
    panel.setLayout(null);

    label = new JLabel("Bookings");
    label.setBounds(600, 10, 840, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.PLAIN, 40));
    panel.add(label);

    removeButton = new JButton("Remove");
    removeButton.addActionListener(this);
    removeButton.setBounds(575, 625, 200, 50);
    removeButton.setFont(new Font("Serif", Font.BOLD, 25));
    removeButton.setBackground(new java.awt.Color(238, 186, 76));
    removeButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(removeButton);

    menuButton = new JButton("Back");
    menuButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent event) {
          bookingFrame.dispose();
        }
      }
    );
    menuButton.setBounds(40, 40, 150, 50);
    menuButton.setFont(new Font("Serif", Font.BOLD, 25));
    menuButton.setBackground(new java.awt.Color(238, 186, 76));
    menuButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(menuButton);

    Scanner reader = new Scanner(bookingsFile);
    bookings = new String[100];
    int i = 0;
    String entry = "";
    while (reader.hasNextLine()) {
      entry = reader.nextLine();
      bookings[i] = entry;
      i++;
    }
    reader.close();

    bookingList = new JList(bookings);
    bookingList.setBounds(75, 140, 1200, 450);
    bookingList.setFixedCellWidth(50);
    bookingList.setFixedCellHeight(50);
    bookingList.setOpaque(true);
    panel.add(bookingList);
    bookingFrame.setVisible(true);
  }

  public void searchBooking(String s) throws FileNotFoundException {
    searchFrame = new JFrame("Airline Reservation System");
    searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    searchFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panel = new JPanel();
    searchFrame.add(panel);
    panel.setLayout(null);

    label = new JLabel("Search Results");
    label.setBounds(550, 10, 640, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.PLAIN, 40));
    panel.add(label);

    searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    searchButton.setBounds(575, 625, 200, 50);
    searchButton.setFont(new Font("Serif", Font.BOLD, 25));
    searchButton.setBackground(new java.awt.Color(238, 186, 76));
    searchButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(searchButton);

    menuButton = new JButton("Back");
    menuButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent event) {
          searchFrame.dispose();
        }
      }
    );
    menuButton.setBounds(40, 40, 150, 50);
    menuButton.setFont(new Font("Serif", Font.BOLD, 25));
    menuButton.setBackground(new java.awt.Color(238, 186, 76));
    menuButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(menuButton);
    Scanner reader = new Scanner(bookingsFile);
    searchResults = new String[100];
    int i = 0;
    int j = 0;
    boolean found = false;
    while (reader.hasNextLine()) {
      String str = reader.nextLine();
      if (str.toLowerCase().contains(s.toLowerCase())) {
        searchResults[j] = str;
        found = true;
        i++;
        j++;
      } else {
        i++;
      }
    }
    reader.close();
    if (!found) {
      JOptionPane.showMessageDialog(null, "No result found");
      return;
    } else {
      searchList = new JList(searchResults);
      searchList.setBounds(75, 140, 1200, 450);
      searchList.setFixedCellWidth(50);
      searchList.setFixedCellHeight(50);
      searchList.setOpaque(true);
      panel.add(searchList);
      searchFrame.setVisible(true);
    }
  }

  public void searchFlight(String s) throws FileNotFoundException {
    searchFrame = new JFrame("Airline Reservation System");
    searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    searchFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    panel = new JPanel();
    searchFrame.add(panel);
    panel.setLayout(null);

    label = new JLabel("Search Results");
    label.setBounds(550, 10, 640, 100);
    label.setForeground(new java.awt.Color(238, 186, 76));
    label.setFont(new Font("Serif", Font.PLAIN, 40));
    panel.add(label);

    searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    searchButton.setBounds(575, 625, 200, 50);
    searchButton.setFont(new Font("Serif", Font.BOLD, 25));
    searchButton.setBackground(new java.awt.Color(238, 186, 76));
    searchButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(searchButton);
    menuButton = new JButton("Back");
    menuButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent event) {
          searchFrame.dispose();
        }
      }
    );
    menuButton.setBounds(40, 40, 150, 50);
    menuButton.setFont(new Font("Serif", Font.BOLD, 25));
    menuButton.setBackground(new java.awt.Color(238, 186, 76));
    menuButton.setForeground(new java.awt.Color(58, 58, 60));
    panel.add(menuButton);
    Scanner reader = new Scanner(flightsFile);
    searchResults = new String[100];
    int i = 0;
    int j = 0;
    boolean found = false;
    while (reader.hasNextLine()) {
      String str = reader.nextLine();
      if (str.toLowerCase().contains(s.toLowerCase())) {
        searchResults[j] = str;
        found = true;
        fromSearch = true;
        i++;
        j++;
      } else {
        i++;
      }
    }
    reader.close();
    if (!found) {
      JOptionPane.showMessageDialog(null, "No result found");
    } else {
      searchList = new JList(searchResults);
      searchList.setBounds(75, 140, 1200, 450);

      searchList.setFixedCellWidth(50);
      searchList.setFixedCellHeight(50);
      searchList.setOpaque(true);
      panel.add(searchList);
      searchFrame.setVisible(true);
    }
  }
}
