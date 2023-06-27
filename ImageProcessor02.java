//The ImageProcessor01 class

//The purpose of this class is to provide a
// simple example of an image processing class
// that is compatible with the program named
// Framework02.

//The constructor for the class displays a small
// frame on the screen with a single textfield.
// The purpose of the text field is to allow the
// user to enter a value that represents the
// radius of a circle.  In operation, the user
// types a value into the text field and then
// clicks the Replot button on the main image
// display frame.  The user is not required to
// press the Enter key after typing the new
// value, but it doesn't do any harm to do so.

//The method named processImage receives a 3D
// array containing alpha, red, green, and blue
// values for an image.  The values are received
// as type int (not type byte).

// The threeDPix array that is received is
// modified to cause a cyan circle to be
// drawn down and to the right from the upper
// left-most corner of the image.  The radius of
// the circle is controlled by the value that is
// typed into the text field.  Initially, this
// value is 1.0.  The image is not modified in
// any other way.

//To cause a new circle to be drawn, type a radius
// value into the text field and click the Replot
// button at the bottom of the image display
// frame.

//This class extends Frame.  However, a
// compatible class is not required to extend the
// Frame class. This example extends Frame
// because it provides a GUI for user data input.

//A compatible class is required to implement the
// interface named Framework02Intfc.

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;

class ImageProcessor01 extends Frame
                  implements Framework02Intfc{

  double radius;//Controls the radius of the circle
  String inputData;//Obtained via the TextField
  TextField inputField;//Reference to TextField

  //Constructor must take no parameters
  ImageProcessor01(){
    //Create and display the user-input GUI.
    setLayout(new FlowLayout());

    Label instructions = new Label(
               "Type a radius value and Replot.");
    add(instructions);

    inputField = new TextField("146.0",5);
    add(inputField);

    setTitle("Michael Brady");
    setBounds(400,0,200,100);
    setVisible(true);

    //Anonymous inner class listener to terminate
    // program.
    this.addWindowListener(
      new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          System.exit(0);//terminate the program
        }//end windowClosing()
      }//end WindowAdapter
    );//end addWindowListener
  }//end constructor

  //The following method must be defined to
  // implement the Framework02Intfc interface.
  public int[][][] processImg(
                             int[][][] threeDPix,
                             int imgRows,
                             int imgCols){

    //Display some interesting information
    System.out.println("Program test");
    System.out.println("Width = " + imgCols);
    System.out.println("Height = " + imgRows);

    //Make a working copy of the 3D array to
    // avoid making permanent changes to the
    // image data.
    int[][][] temp3D =
                    new int[imgRows][imgCols][4];
    for(int row = 0;row < imgRows;row++){
      for(int col = 0;col < imgCols;col++){
        temp3D[row][col][0] =
                          threeDPix[row][col][0];
        temp3D[row][col][1] =
                          threeDPix[row][col][1];
        temp3D[row][col][2] =
                          threeDPix[row][col][2];
        temp3D[row][col][3] =
                          threeDPix[row][col][3];
      }//end inner loop
    }//end outer loop

    //Get radius value from the TextField
    try {
      radius = Double.parseDouble(inputField.getText());
    } catch (NumberFormatException e) {
      System.out.println("java.lang.NumberFormatException ignored");
    }
    
    //Get center of image
    int rowCenter = imgRows/2;
    int colCenter = imgCols/2;

    if(radius > 0){
      //Draw a cyan circle on the image
    for(int col = 0;col < imgCols;col++){
      //Fill once for each side of circle
      for(int i = 0; i < 2; i++){
        int row;
        if(i == 0){
          //Rows of top side
          row = (int)(-1*(Math.sqrt((radius*radius) - Math.pow((col - colCenter), 2))) + rowCenter);
        }
        else{
          //Rows of bottom side
          row = (int)(Math.sqrt((radius*radius) - Math.pow((col - colCenter), 2)) + rowCenter);
        }
        if(row > imgRows -1)break;
        //Set values for alpha, red, green, and
        // blue colors.
        if(row >= 0){
          temp3D[row][col][0] = (byte)0;
          temp3D[row][col][1] = (byte)0xff;
          temp3D[row][col][2] = (byte)0xff;
          temp3D[row][col][3] = (byte)0xff;
        }
        //Fill in missing spaces
        int nextRow;
        if(i == 0){
          //Next row of top side
          nextRow = (int)(-1*(Math.sqrt((radius*radius) - Math.pow(((col + 1) - colCenter), 2))) + rowCenter);
        }
        else{
          //Next row of bottom side
          nextRow = (int)(Math.sqrt((radius*radius) - Math.pow(((col + 1) - colCenter), 2)) + rowCenter);
        }
        //Get difference between row and nextRow
        int diff = nextRow - row;
        if(row + diff <= imgRows && row - diff > 0 && nextRow != 0 && row != 0){
          if(diff > 1){
            for(int a = 1; a <= diff; a++){
              temp3D[row + a][col][0] = (byte)0;
              temp3D[row + a][col][1] = (byte)0xff;
              temp3D[row + a][col][2] = (byte)0xff;
              temp3D[row + a][col][3] = (byte)0xff;
            }
          }else if(diff < 0){
            for(int a = 1; a <= (diff * -1); a++){
              temp3D[row - a][col][0] = (byte)0;
              temp3D[row - a][col][1] = (byte)0xff;
              temp3D[row - a][col][2] = (byte)0xff;
              temp3D[row - a][col][3] = (byte)0xff;
            }
          }
        }
      }
    }//end for loop
    }
    //Return the modified array of image data.
    return temp3D;
  }//end processImg
}//end class ImageProcessor01