# HierarchyOfShapes
User who desires to view different types of Shapes.
Primary Actor: End User who uses the program to display different types of Shapes.
Stakeholders and Interest:
End User: who desires to view different types of Shapes.
Programmer: executes without any errors and fully functional.
Preconditions:
1. User has input the text file in the below format
LineSegment ( x1 , y1 ) and ( x2 , y2 )
Rectangle x and y ( width and height )
Circle ( x , y ) ( radius )
2. User has to provide the coordinates randomly in the Text Field to draw the shapes.
Success Guarantee – Post Conditions:
The program displays the shapes providing the input either through text file or entering the coordinates desired, which displays the shapes accordingly.
Main Success Scenario (Basic Flow): Input Events from Actor: End User System Events and Responses
Click on File -> Open
Prompts to select a Text File
Selected File and Confirmed
Displays the different kind of shapes as per the input from Text File with random colors and shapes moving.
Click on "Draw Shapes"
Request to select the Type of Shape
Shape selected
Based on the selection, need to input the coordinates, width and height
Click "Submit"
Displays the shape requested which is in random colors and shapes in motion.
Extensions or Alternative Scenario: 1. Error Message is generated along with sound for the following exceptions:
a. When no file is uploaded after clicking on “File  Open”.
b. When all the coordinates are given the value as Zero.
c. When negative numbers are entered for coordinates.
