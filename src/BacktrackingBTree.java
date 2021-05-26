import java.util.List;
import java.util.LinkedList;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

    //You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Reminder!
        LinkedList<T> list =(LinkedList<T>) this.lastOneEnter.removeFirst();
        if (!list.isEmpty()) {
            T valueToDelete = list.removeFirst();
            Node<T> curr = this.getNode(valueToDelete);
            curr.removeKey(valueToDelete);
            while (!list.isEmpty()) {
                T valueToMerge = list.removeLast();
                while(curr!=null && curr.indexOf(valueToMerge)==-1) {
                    curr=curr.parent;
                }
                if(curr!=null) {
                    merge(curr, curr.indexOf(valueToMerge));
                }

            }
        }

        if(root!= null && root.numOfKeys==0) {
            root = root.children[0];
            if(root!= null && root.numOfKeys==0) root=null;
        }
    }

    private void merge(Node<T> curr, int index) {
        T merge1= curr.getKey(index);
        Node<T> ToMerge= curr.getChild(index);
        Node<T> ToMerge1= curr.getChild(index+1);
        Node<T> merge=new Node<>(curr, curr.keys.length);
        merge.addKey(merge1);
        for (int i = 0; i < ToMerge1.numOfKeys; i++) {
            merge.addKey(ToMerge1.getKey(i));
        }
        for (Node<T> node:ToMerge1.children) {
            if(node!=null)
                merge.addChild(node);
        }
        for (int i = 0; i < ToMerge.numOfKeys; i++) {
            merge.addKey(ToMerge.getKey(i));
        }
        for (Node<T> node:ToMerge.children) {
            if(node!=null)
                merge.addChild(node);
        }
        curr.removeChild(ToMerge);
        curr.addChild(merge);
        curr.removeChild(ToMerge1);
        if (curr.numOfKeys>=1) {
            curr.removeKey(index);
        }
        else{
            curr=curr.children[0];
        }
    }
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> BTreeBacktrackingCounterExample() {
        List<Integer> ls= new LinkedList<>();
        ls.add(1);
        ls.add(2);
        ls.add(3);
        ls.add(4);
        return ls;
    }
}
