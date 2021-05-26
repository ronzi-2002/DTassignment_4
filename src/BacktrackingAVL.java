import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Do not remove this line
        String rot=rotations.pop();
        if(!rot.equals("null"))
        {

            Node unBal=nodesInserted.pop();//node that was out of balance
            if (rot.equals(ImbalanceCases.LEFT_LEFT.toString())) {
                //

                if (unBal==root)
                    root= leftRotate(unBal);
                else {
                    if(unBal.parent.left==unBal)
                        unBal.parent.left = leftRotate(unBal);
                    else
                        unBal.parent.right = leftRotate(unBal);

                }
            }
            else if (rot.equals(ImbalanceCases.RIGHT_RIGHT.toString())) {
                if (unBal==root)
                    root= rightRotate(unBal);
                else {
                    if(unBal.parent.right==unBal)
                        unBal.parent.right = rightRotate(unBal);
                    else
                        unBal.parent.left = rightRotate(unBal);

                }

            }
            else if  (rot.equals(ImbalanceCases.RIGHT_LEFT.toString()))
            {
                if (unBal==root)
                    root= rightRotate(unBal);
                else {
                    if(unBal.parent.right==unBal)
                        unBal.parent.right = rightRotate(unBal);
                    else
                        unBal.parent.left = rightRotate(unBal);
                }
                if(unBal.parent.left==unBal)
                    unBal.parent.left = leftRotate(unBal);
                else
                    unBal.parent.right = leftRotate(unBal);
            }
            else//LEFT_RIGHT
            {
                //Node unBalLeft=nodesInserted.pop();//node that was out of balance
                //Node unBal=nodesInserted.pop();//node that was out of balance
                if (unBal==root)
                    root= leftRotate(unBal);
                else {
                    if(unBal.parent.left==unBal)
                        unBal.parent.left = leftRotate(unBal);
                    else
                        unBal.parent.right = leftRotate(unBal);
                }
                //printTree();
                if(unBal.parent.right==unBal)
                    unBal.parent.right = rightRotate(unBal);
                else
                    unBal.parent.left = rightRotate(unBal);
            }

        }

        Node forDeletion=nodesInserted.pop();
        //after deleting the node we need to update the heights so we will hold the parent
        if (forDeletion.parent==null)//its the root and the only node in the tree
        {
            root=null;
        }
        else {

            if(forDeletion.parent.right==forDeletion)
                forDeletion.parent.right=null;
            else
                forDeletion.parent.left=null;

        }
        Node per=forDeletion.parent;
        boolean changed=true;
        while (per != null && changed ) {
            int newHeight=Math.max(getNodeHeight(per.left), getNodeHeight(per.right)) + 1;
            if(newHeight!=per.height)
                per.height = newHeight;
            else
                changed=false;
            per=per.parent;
            }
    } 
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample(){
        IntegrityStatement.signature(); // Do not remove this line
        LinkedList<Integer>answer=new LinkedList<>();
        answer.addLast(1);
        answer.addLast(2);
        answer.addLast(3);
        return answer;

    }
}
