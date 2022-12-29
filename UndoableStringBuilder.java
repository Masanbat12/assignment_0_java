package observer;

/*
Use the class you've implemented in previous assignment
 */
    import java.util.ArrayList;
import java.util.List;

    /**
     * Similar to the built-in StringBuilder class.
     * Difference is, this class allows the use of an 'undo' operation.
     * @author Daniel Makmal
     * @author Masnebet Molu
     */
    public class UndoableStringBuilder {

        /**
         * Simple String field value.
         * All operations will involve manipulating this String.
         */
        private String str;
        /**
         * This field is used to track the different operations performed by the user of the class.
         * This will allow the use of the 'undo' method.
         */
        private List<String> previousValues = new ArrayList<>();

        /**
         * Creates a new object of type 'UndoableStringBuilder'.
         * @param str A string for the initialization of 'UndoableStringBuilder' object.
         */
        public UndoableStringBuilder(String str) {

            this.str = str;
            previousValues.add(this.str);
        }

        /**
         * Creates a new object of type 'UndoableStringBuilder'.
         * No parameters. String field set to: "".
         */
        public UndoableStringBuilder() {

            str = "";
            previousValues.add(this.str);
        }

        /**
         * To-String function. Used for printing contents of current object.
         * @return This String field.
         */
        @Override
        public String toString() {
            return str;
        }

        /**
         * Appends a String value (parameter) to the end of the current String field value.
         * @param str Appends 'str' String value to the end of this object's String field.
         * @return this object, with the updated String value.
         */
        public UndoableStringBuilder append(String str){

            this.str += str;
            previousValues.add(this.str);

            return this;
        }

        /**
         * Deletes a sub-sequence of characters within this objects's String value.
         * Note that the public method does not expose the boolean flag
         * (The user cannot interact with this variable).
         * @param start Index position. The characters from this point until 'end-1' will be deleted.
         * @param end Index position. The characters up until 'end-1' will be deleted.
         * @return this object, with the updated String value.
         */
        public UndoableStringBuilder delete(int start, int end){

            return delete(start, end, true);
        }

        /**
         * Deletes a sub-sequence of characters within this objects's String value.
         * @param start Index position. The characters from this point until 'end-1' will be deleted.
         * @param end Index position. The characters up until 'end-1' will be deleted.
         * @param flag Used to determine whether to save this operation in 'previousValues'.
         *             Connected directly to the 'undo' method.
         * @return this object, with the updated String value.
         */
        private UndoableStringBuilder delete(int start, int end, boolean flag){

            StringIndexOutOfBoundsException exception = new StringIndexOutOfBoundsException();

            if (start < 0 || end > str.length()) throw exception;

            String newStr = "";

            if (start == end) return this;

            for (int i = 0; i < str.length(); i++){

                if ((i >= start) && (i < end)) continue;

                newStr += str.charAt(i);
            }

            str = newStr;

            if (flag) previousValues.add(this.str);

            return this;
        }


        /**
         * Inserts a sequence of characters into this field String value.
         * The position of insertion is determined by the user.
         * Can be at the start, end, or middle of the character sequence.
         * Note that the public method does not expose the boolean flag
         * (The user cannot interact with this variable).
         * @param offset Index position. Determines where to insert the String value.
         * @param str String value to be inserted into this sequence of characters.
         * @return this object, with the updated String value.
         */
        public UndoableStringBuilder insert(int offset, String str){

            return insert(offset, str, true);
        }

        /**
         * Inserts a sequence of characters into this field String value.
         * The position of insertion is determined by the user.
         * Can be at the start, end, or middle of the character sequence.
         * @param offset Index position. Determines where to insert the String value.
         * @param str String value to be inserted into this sequence of characters.
         * @param flag Used to determine whether to save this operation in 'previousValues'.
         *        Connected directly to the 'undo' method.
         * @return this object, with the updated String value.
         */
        private UndoableStringBuilder insert(int offset, String str, boolean flag){

            String newStr;

            if ( (offset >= 0) && (offset < this.str.length()) ){

                String[] strings = splitAt(offset, this.str);
                newStr = "".concat(strings[0]).concat(str).concat(strings[1]);

            } else {

                StringIndexOutOfBoundsException exception = new StringIndexOutOfBoundsException();
                throw exception;
            }

            this.str = newStr;
            if (flag) previousValues.add(this.str);

            return this;
        }


        /**
         * This is an assistant function.
         * Splits a String value at a specified position.
         * @param index Where to split the String.
         * @param str A String value to be split.
         * @return A String Array of two values. Combined, they are the original String.
         */
        private String[] splitAt(int index, String str){

            char[] charSequence = str.toCharArray();
            String[] strings = new String[2];

            String first = "";
            String second = "";

            for (int i = 0; i < charSequence.length; i++){

                if (i > index){

                    second += charSequence[i];
                    continue;
                }
                first += charSequence[i];
            }

            strings[0] = first;
            strings[1] = second;

            return strings;
        }


        /**
         * Replace a sub-sequence of characters of this object's field, with another sequence.
         * @param start Index position. The characters from this point until 'end-1' will be replaced.
         * @param end Index position. The characters up until 'end-1' will be replaced.
         * @param str String value (sequence of characters) to replace a sub-sequence of the object's String field.
         * @return this object, with the updated String value.
         */
        public UndoableStringBuilder replace(int start, int end, String str){

            this.delete(start, end, false);
            this.insert(start-1, str, false);

            previousValues.add(this.str);
            return this;
        }


        /**
         * Reverse this object's String field value.
         * @return this object, with the updated String value.
         */
        public UndoableStringBuilder reverse(){

            str = reverse(str);
            previousValues.add(this.str);

            return this;
        }

        /**
         * Assistant function. Used to simply reverse a String value.
         * @param str String value to be reversed.
         * @return reversed String value.
         */
        private String reverse(String str){

            String reversed = "";

            for (int i = str.length()-1; i >= 0; i--) reversed += str.charAt(i);

            return reversed;
        }


        /**
         * Undo operation. Erase the last operation performed on the object.
         */
        public void undo(){

            // Checking to see if an 'undo' operation is possible.
            // If size of list is 1, then there are no operations to undo.
            if (previousValues.size() == 1) return;

            int lastPosition = previousValues.size() - 1;

            previousValues.remove(lastPosition);

            str = previousValues.get(lastPosition-1);
        }
    }