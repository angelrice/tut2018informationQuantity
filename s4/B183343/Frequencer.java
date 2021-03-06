package s4.B183343;
import s4.specification.*;

/*package s4.specification;
   public interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte  target[]); // set the data to search.
    void setSpace(byte  space[]);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or SPACE's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
   }
 */


public class Frequencer implements FrequencerInterface {
        // Code to start with: This code is not working, but good start point to work.
        byte [] myTarget;
        byte [] mySpace;

        boolean targetReady = false;
        boolean spaceReady = false;

        int [] suffixArray;

        // The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
        // Each suffix is expressed by a integer, which is the starting position in mySpace.
        // The following is the code to print the variable
        private void printSuffixArray() {
                if(spaceReady) {
                        for(int i=0; i< mySpace.length; i++) {
                                int s = suffixArray[i];
                                for(int j=s; j<mySpace.length; j++) {
                                        System.out.write(mySpace[j]);
                                }
                                System.out.write('\n');
                        }
                }
        }

        private int suffixCompare(int i, int j) { //sort dictionary
                // comparing two suffixes by dictionary order.
                // i and j denoetes suffix_i, and suffix_j
                // if suffix_i > suffix_j, it returns 1
                // if suffix_i < suffix_j, it returns -1
                // if suffix_i = suffix_j, it returns 0;
                // It is not implemented yet,
                // It should be used to create suffix array.
                // Example of dictionary order
                // "i"      <  "o"        : compare by code
                // "Hi"     <  "Ho"       ; if head is same, compare the next element
                // "Ho"     <  "Ho "      ; if the prefix is identical, longer string is big
                //
                // ****  Please write code here... ***

                int k=i;
                int l=j;

                String strspace = new String(mySpace);

                byte[] suffix_i_all = strspace.substring(i).getBytes();
                byte[] suffix_j_all = strspace.substring(j).getBytes();
                int limit=0;
                boolean flag;


                if(suffix_i_all.length < suffix_j_all.length) {
                        limit = suffix_i_all.length;
                        flag=false;

                }else{
                        limit = suffix_j_all.length;
                        flag=true;
                }

                for(int a=0; a<limit; a++) {
                        if(mySpace[k] > mySpace[l]) {
                                return 1;
                        }else if(mySpace[k] < mySpace[l]) {
                                return -1;
                        }

                        k++;
                        l++;
                }

                if(flag==true) {
                        return 1;
                }else {
                        return -1;
                }

        }

        private void merge(int[] merge1,int[] merge2,int[] sortarray){
                int i=0;
                int j=0;

                while(i<merge1.length || j<merge2.length) {
                        if(j>=merge2.length || (i<merge1.length && suffixCompare(merge1[i],merge2[j])==-1)) {
                                sortarray[i+j]=merge1[i];
                                i++;
                        }else{
                                sortarray[i+j]=merge2[j];
                                j++;
                        }
                }
        }

        private void mergesort(int[] sortarray){
                if(sortarray.length>1) {
                        int m=sortarray.length/2;
                        int n=sortarray.length-m;
                        int[] merge1 = new int[m];
                        int[] merge2 = new int[n];

                        for(int i=0; i<m; i++) {
                                merge1[i]=sortarray[i];
                        }

                        for(int i=0; i<n; i++) {
                                merge2[i]=sortarray[m+i];
                        }

                        mergesort(merge1);
                        mergesort(merge2);
                        merge(merge1,merge2,sortarray);
                }
        }

        public void setSpace(byte [] space) { //Make dictionary
                mySpace = space;
                if(mySpace.length>0) spaceReady = true;
                suffixArray = new int[space.length];
                // put all suffixes  in suffixArray. Each suffix is expressed by one integer.
                for(int i = 0; i< space.length; i++) {
                        suffixArray[i] = i;
                }

                // System.out.println("----start\n");
                // printSuffixArray();
                //   System.out.println("----\n\n\n");

                // Sorting is not implmented yet.
                //
                //
                // ****  Please write code here... ***

//bubble sort
                // for(position=0; position<mySpace.length-1; position++) {
                //
                //         for(j=mySpace.length-1; j>position; j--) {
                //
                //                 if(suffixCompare(suffixArray[j-1], suffixArray[j])==1) {
                //
                //                         buf = suffixArray[j-1];
                //                         suffixArray[j-1]=suffixArray[j];
                //                         suffixArray[j]=buf;
                //
                //                 }
                //
                //         }
//		  System.out.println("----"+position+"\n");
//		  printSuffixArray();
//		  System.out.println("----\n\n\n");
//
                // }
                // printSuffixArray();
                // System.out.println("----------------------\n");
                mergesort(suffixArray);

        }

        private int targetCompare(int i, int start, int end) { //dictionary search
                // comparing suffix_i and target_j_end by dictonary order with limitation of length;
                // if the beginning of suffix_i matches target_j_end, and suffix is longer than target  it returns 0;
                // if suffix_i > target_j_end it return 1;
                // if suffix_i < target_j_end it return -1
                // It is not implemented yet.
                // It should be used to search the apropriate index of some suffix.
                // Example of search
                // suffix i          target j~end
                // "o"       >     "i"
                // "o"       <     "z"
                // "o"       =     "o"
                // "o"       <     "oo"
                // "Ho"      >     "Hi"
                // "Ho"      <     "Hz"
                // "Ho"      =     "Ho"
                // "Ho"      <     "Ho "   : "Ho " is not in the head of suffix "Ho"
                // "Ho"      =     "H"     : "H" is in the head of suffix "Ho"
                //
                // ****  Please write code here... ***

                //liner
                // for(int a=suffixArray[i]; start<end; a++,start++) {
                //         if(mySpace[a]>myTarget[start]) {
                //                 return 1;
                //         }else if(mySpace[a]<myTarget[start] || mySpace.length < end-start) {
                //                 return -1;
                //         }else if(mySpace[a]==myTarget[start] && mySpace.length-a < end-start) {
                //                 return -1;
                //         }
                // }


                // binary search
                int mid = i;
                for(int a=suffixArray[mid]; start<end; a++,start++) {
                        if(mySpace[a]>myTarget[start]) {
                                return 1;
                        }else if(mySpace[a]<myTarget[start] || mySpace.length < end-start) {
                                return -1;
                        }else if(mySpace[a]==myTarget[start] && mySpace.length-a < end-start) {
                                return -1;
                        }
                }


                return 0; // This line should be modified.
        }

        private int subByteStartIndex(int start, int end) {
                // It returns the index of the first suffix which is equal or greater than subBytes;
                // not implemented yet;
                // For "Ho", it will return 5  for "Hi Ho Hi Ho".
                // For "Ho ", it will return 6 for "Hi Ho Hi Ho".
                //
                // ****  Please write code here... ***

                int mid = 0;
                int left=0;
                int right=suffixArray.length-1;

                //liner
                // for(int i = 0; i<suffixArray.length; i++) {
                //         if(targetCompare(i,start,end)==0) {
                //                 return i;
                //         }
                // }

                //binary
                while(left<=right) {
                        mid=(left+right)/2;
                        int result=targetCompare(mid,start,end);
                        if(result==-1) {
                                left = mid + 1;
                        }else if(result==1) {
                                right = mid - 1;
                        }else if(result==0) {
                                if(mid-1<0) {
                                        return 0;
                                }
                                if(targetCompare(mid-1,start,end)!=-1) {
                                        right = mid - 1;
                                }else{
                                        // System.out.println("start:"+mid);
                                        return mid;
                                }
                        }
                }
                return suffixArray.length; // This line should be modified.
        }

        private int subByteEndIndex(int start, int end) {
                // It returns the next index of the first suffix which is greater than subBytes;
                // not implemented yet
                // For "Ho", it will return 7  for "Hi Ho Hi Ho".
                // For "Ho ", it will return 7 for "Hi Ho Hi Ho".
                //
                // ****  Please write code here... ***

                int mid = 0;
                int left=0;
                int right=suffixArray.length-1;

                //liner
                // for(int i = 0; i<suffixArray.length; i++) {
                //         if(targetCompare(i,start,end)==1) {
                //                 return i;
                //         }
                // }

                //binary
                while(left<=right) {
                        mid=(left+right)/2;
                        int result=targetCompare(mid,start,end);
                        if(result==-1) {
                                left = mid + 1;
                        }else if(result==1) {
                                right = mid - 1;
                        }else if(result==0) {
                                if(mid+1>suffixArray.length-1) {
                                        return suffixArray.length;
                                }
                                if(targetCompare(mid+1,start,end)!=1) {
                                        left = mid + 1;
                                }else{
                                        // System.out.println("end:"+mid);
                                        return mid+1;
                                }
                        }

                }

                return suffixArray.length; // This line should be modified.
        }

        public int subByteFrequency(int start, int end) {
                /* This method be work as follows, but
                   int spaceLength = mySpace.length;
                   int count = 0;
                   for(int offset = 0; offset< spaceLength - (end - start); offset++) {
                    boolean abort = false;
                    for(int i = 0; i< (end - start); i++) {
                    if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
                    }
                    if(abort == false) { count++; }
                   }
                 */
                int first = subByteStartIndex(start, end);
                int last1 = subByteEndIndex(start, end);
                return last1 - first;
        }

        public void setTarget(byte [] target) {
                myTarget = target; if(myTarget.length>0) targetReady = true;
        }

        public int frequency() {
                if(targetReady == false) return -1;
                if(spaceReady == false) return 0;
                return subByteFrequency(0, myTarget.length);
        }

        public static void main(String[] args) {
                Frequencer frequencerObject;
                try {
                        frequencerObject = new Frequencer();
                        frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
                        // frequencerObject.printSuffixArray(); // you may use this line for DEBUG
                        /* Example from "Hi Ho Hi Ho"
                           0: Hi Ho
                           1: Ho
                           2: Ho Hi Ho
                           3:Hi Ho
                           4:Hi Ho Hi Ho
                           5:Ho
                           6:Ho Hi Ho
                           7:i Ho
                           8:i Ho Hi Ho
                           9:o
                           A:o Hi Ho
                         */

                        frequencerObject.setTarget("H".getBytes());

                        // ****  Please write code to check subByteStartIndex, and subByteEndIndex
                        // System.out.println(frequencerObject.targetCompare(0,0,end ));
                        // System.out.println(frequencerObject.targetCompare(1,0,end ));
                        // System.out.println(frequencerObject.targetCompare(2,0,end ));
                        // System.out.println(frequencerObject.targetCompare(3,0,end ));
                        // System.out.println(frequencerObject.targetCompare(4,0,end ));
                        // System.out.println(frequencerObject.targetCompare(5,0,end ));
                        // System.out.println(frequencerObject.targetCompare(6,0,end ));
                        // System.out.println(frequencerObject.targetCompare(7,0,end ));
                        // System.out.println(frequencerObject.targetCompare(8,0,end ));
                        // System.out.println(frequencerObject.targetCompare(9,0,end ));
                        // System.out.println(frequencerObject.targetCompare(10,0,end ));


                        int result = frequencerObject.frequency();
                        System.out.print("Freq = "+ result+" ");
                        if(4 == result) { System.out.println("OK"); } else {System.out.println("WRONG"); }
                }
                catch(Exception e) {
                        System.out.println("STOP");
                        System.out.println(e);
                }
        }
}
