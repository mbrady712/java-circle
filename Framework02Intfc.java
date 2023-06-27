//34567890123456789012345678901234567890123456789
//Note: this is wide format for small fonts.
//=============================================//

/*File Framework02Intfc.java
Copyright 2004, R.G.Baldwin

The purpose of this interface is to declare
the one method required by image processing
classes that are compatible with the program
named Framework02.java.

Tested using SDK 1.4.2 under WinXP
===============================================*/

interface Framework02Intfc{
    int[][][] processImg(int[][][] threeDPix,
                         int imgRows,
                         int imgCols);
  }//end Framework02Intfc
  
  //=============================================//
