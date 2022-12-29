# assignment_0_java_
## assignment_0 in oop course
### Masanbat_Mulu_work

The assignment was to write my own StringBuilder class with the support of the undo operation.
To do this, i had to delegate all the methods to the standard StringBuilder,
and keep a list of all operations to perform undo(),in my own class .
An undo() method had to be written for the following methods:

/1/Appends the specified string to this character sequence.
1. public UndoableStringBuilder append(String str).

/2/Removes the characters in a substring of this sequence.                                                                                                              //The substring begins at the specified start and extends to the character at index end - 1 or to the end of 
//the sequence if no such character exists. If start is equal to end, no changes are made.
2. public UndoableStringBuilder delete(int start, int end).

/3/Inserts the string into this character sequence.
3. public UndoableStringBuilder insert(int offset, String str).

/4/Replaces the characters in a substring of this sequence with characters in the specified String. The substring begins at the specified start and extends to the //character at index end - 1 or to the end of the sequence if no such character exists. First the characters in the substring are removed and then the specified String //is inserted at start. (This sequence will be lengthened to accommodate the specified String if necessary).
4. public UndoableStringBuilder replace(int start,int end, String str).

/5/ Causes this character sequence to be replaced by the reverse of the sequence.
5. public UndoableStringBuilder reverse().

for example the output and out put should look like this:

public static void main(String[] args) {
UndoableStringBuilder usb = new UndoableStringBuilder();

usb.append("to be or not to be");
System.out.println(usb);

usb.replace(3, 5, "eat");
System.out.println(usb);

usb.replace(17, 19, "eat");
System.out.println(usb);

usb.reverse();
System.out.println(usb);

usb.undo ();
System.out.println(usb);

usb.undo();
System.out.println(usb) ;

usb.undo();
System.out.println(usb) ;

usb.undo();
System.out.println(usb) ;

OUTPUT:
to be or not to be
to eat or not to be
to eat or not to eat
tae ot ton ro tae ot

to eat or not to eat
to eat or not to be
to be or not to be

