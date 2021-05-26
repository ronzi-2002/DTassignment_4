public class main {
    public static void main(String[]args)
    {
        //testLeftRightYesRoot();
        //testRightLeftNotRoot();
        //testRightRootNotRoot();
        //testRightLeftTwiceNotRoot();
        //testTWOInARow();
        //LEFTLEFTH4();
        Debuging();
    }
    static void testLeftRightNotRoot()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(7);
        t.insert(8);
        t.insert(6);
        t.insert(3);

        //t.insert(2);
        t.insert(4);
        t.printTree();
        //t.insert(8);
        //t.printTree();
        t.Backtrack();
        t.printTree();
    }
    static void testLeftRightYesRoot()
    {
        BacktrackingAVL t=new BacktrackingAVL();

        t.insert(6);
        t.insert(3);

        //t.insert(2);
        t.insert(4);
        t.printTree();
        //t.insert(8);
        //t.printTree();
        t.Backtrack();
        t.printTree();
    }
    static void testRightLeftNotRoot()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(2);
        t.insert(1);
        t.insert(3);
        t.insert(6);

        //t.insert(2);
        t.insert(4);
        t.printTree();
        //t.insert(8);
        //t.printTree();
        t.Backtrack();
        t.printTree();
    }
    static void testRightRootNotRoot()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(2);
        t.insert(1);
        t.insert(3);

        //t.insert(2);
        t.insert(4);
        t.insert(6);
        t.printTree();
        //t.insert(8);
        //t.printTree();
        t.Backtrack();
        t.printTree();
    }
    static void testRightLeftTwiceNotRoot()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(2);
        t.insert(6);
        t.insert(4);

        //t.insert(2);
        t.insert(10);
        t.insert(8);
        t.printTree();
        //t.insert(8);
        //t.printTree();
        t.Backtrack();
        t.printTree();
      t.Backtrack();
      t.printTree();
    }
    static void testTWOInARow()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(2);
        t.insert(6);

        t.insert(4);
        t.insert(10);
        t.printTree();
        t.Backtrack();
        t.printTree();
        t.Backtrack();
        t.printTree();
        //t.insert(2);

    }
    static void LEFTLEFTH4()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(10);
        t.insert(12);
        t.insert(13);
        t.insert(14);
        t.insert(8);
        t.insert(9);
        t.printTree();
        t.insert(6);
        t.insert(4);

        //t.insert(4);
        t.printTree();
        t.Backtrack();
        t.printTree();
        t.Backtrack();
        t.printTree();
        //t.insert(2);

    }
    static void Debuging()
    {
        BacktrackingAVL t=new BacktrackingAVL();
        t.insert(251);
        t.insert(133);
        t.insert(446);
        t.insert(98);
        t.insert(198);
        t.insert(410);
        t.printTree();
        t.insert(278);
        //t.insert(4);

        //t.insert(4);
        t.printTree();
        t.Backtrack();
        t.printTree();
        //t.Backtrack();
        //t.printTree();
        //t.insert(2);

    }

}
