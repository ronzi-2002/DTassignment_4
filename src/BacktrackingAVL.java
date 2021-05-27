import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Do not remove this line
        String rot=rotations.pop();//indicates the rotations made for the last insert
        if(!rot.equals("null"))//if any rotation was made
        {

            Node unBal=nodesInserted.pop();//node that was out of balance(the one rotations were made on.
            if (rot.equals(ImbalanceCases.LEFT_LEFT.toString()))//if the last case was left left->we need to rotate once left to backtrack
            {
                if (unBal==root)
                    root= leftRotate(unBal);
                else {
                    if(unBal.parent.left==unBal)
                        unBal.parent.left = leftRotate(unBal);
                    else
                        unBal.parent.right = leftRotate(unBal);
                }
            }
            else if (rot.equals(ImbalanceCases.RIGHT_RIGHT.toString())) //if the last case was right right->we need to rotate once right to backtrack
            {
                if (unBal==root)
                    root= rightRotate(unBal);
                else {
                    if(unBal.parent.right==unBal)
                        unBal.parent.right = rightRotate(unBal);
                    else
                        unBal.parent.left = rightRotate(unBal);

                }

            }
            else if  (rot.equals(ImbalanceCases.RIGHT_LEFT.toString()))//if the last case was right left->we need to rotate once right and then once left to backtrack
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
            else//LEFT_RIGHT. //if the last case was left right->we need to rotate once left and then once right to backtrack
            {
                if (unBal==root)
                    root= leftRotate(unBal);
                else {
                    if(unBal.parent.left==unBal)
                        unBal.parent.left = leftRotate(unBal);
                    else
                        unBal.parent.right = leftRotate(unBal);
                }
                if(unBal.parent.right==unBal)
                    unBal.parent.right = rightRotate(unBal);
                else
                    unBal.parent.left = rightRotate(unBal);
            }

        }

        Node forDeletion=nodesInserted.pop();//the actual node that was inserted, notice after we backtracked the rotations, the node we need to delete is always a leaf(because we insert new nodes as leafs before rotating)

        if (forDeletion.parent==null)//its the root and the only node in the tree
        {
            root=null;
        }
        else//if the node isn't the root, the only thing we need to do is "remove" the pointer from the parent to the node.
        {
            if(forDeletion.parent.right==forDeletion)
                forDeletion.parent.right=null;
            else
                forDeletion.parent.left=null;

        }
        //after deleting the node we need to update the heights so we will hold the parent
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
