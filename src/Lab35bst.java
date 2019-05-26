
import java.util.*;


public class Lab35bst
{
    public static void main(String args[])
    {
        ExpressionNode root = ExpressionTree.createTree();

        System.out.println("Original Tree");
        System.out.println("=============");
        System.out.print("\nIn-Fix Notation:    ");
        ExpressionTree.inOrderTraverse(root);
        System.out.print("\n\nPre-Fix Notation:   ");
        ExpressionTree.preOrderTraverse(root);
        System.out.print("\n\nPost-Fix Notation:  ");
        ExpressionTree.postOrderTraverse(root);
        System.out.println("\n");

        System.out.println("This tree has "+
                ExpressionTree.operatorCount(root)+" operators and "+
                ExpressionTree.numberCount(root)+" numbers, and evaluates to "+
                ExpressionTree.evaluate(root)+"\n\n");

        ExpressionNode mirrorRoot = ExpressionTree.mirror(root);

        System.out.println("\nMirror Tree");
        System.out.println("===========");
        System.out.print("\nIn-Fix Notation:    ");
        ExpressionTree.inOrderTraverse(mirrorRoot);
        System.out.print("\n\nPre-Fix Notation:   ");
        ExpressionTree.preOrderTraverse(mirrorRoot);
        System.out.print("\n\nPost-Fix Notation:  ");
        ExpressionTree.postOrderTraverse(mirrorRoot);
        System.out.println("\n");

        System.out.println("This tree has "+
                ExpressionTree.operatorCount(mirrorRoot)+" operators and "+
                ExpressionTree.numberCount(mirrorRoot)+" numbers, and evaluates to "+
                ExpressionTree.evaluate(mirrorRoot)+"\n");
    }
}


class ExpressionNode
{
    public ExpressionNode(double initNum, char initOpr, ExpressionNode initLeft, ExpressionNode initRight)
    {
        num = initNum;
        opr = initOpr;
        left = initLeft;
        right = initRight;
    }

    public double getNum()								{ return num; 			}
    public char getOpr()								{ return opr;			}
    public ExpressionNode getLeft()						{ return left; 			}
    public ExpressionNode getRight()					{ return right; 		}
    public void setNum(int theNewNum)					{ num = theNewNum; 		}
    public void setOpr(char theNewOpr)					{ opr = theNewOpr;		}
    public void setLeft(ExpressionNode theNewLeft)		{ left = theNewLeft; 	}
    public void setRight(ExpressionNode theNewRight)	{ right = theNewRight; 	}

    private double num;
    private char opr;
    private ExpressionNode left;
    private ExpressionNode right;
}





class ExpressionTree
{

    public static ExpressionNode createTree()
    {
        ExpressionNode n12  = new ExpressionNode(12,' ',null,null);
        ExpressionNode n6   = new ExpressionNode( 6,' ',null,null);
        ExpressionNode ndiv = new ExpressionNode( 0,'/',n12,n6);
        ExpressionNode n7   = new ExpressionNode( 7,' ',null,null);
        ExpressionNode nadd = new ExpressionNode( 0,'+',n7,ndiv);
        ExpressionNode n2   = new ExpressionNode( 2,' ',null,null);
        ExpressionNode n4   = new ExpressionNode( 4,' ',null,null);
        ExpressionNode ncar = new ExpressionNode( 0,'^',n2,n4);
        ExpressionNode n5   = new ExpressionNode( 5,' ',null,null);
        ExpressionNode nsub = new ExpressionNode( 0,'-',ncar,n5);
        ExpressionNode root = new ExpressionNode( 0,'*',nadd,nsub);
        return root;
    }

    public static void inOrderTraverse (ExpressionNode p)
    {


        double result, operand1, operand2;
        double tempN = 0;
        char tempO = ' ';

        if (p==null)
            result = 0;
        else
        {
            if (isNum(p))
                tempN = p.getNum();
            else
                tempO = p.getOpr();
            if (!isNum(p))
            {
                System.out.print("( ");
                inOrderTraverse(p.getLeft());
                System.out.print(" "+tempO+" ");
                inOrderTraverse(p.getRight());
                System.out.print(" )");

            }
            else
                System.out.print(tempN);
        }


    }




    public static void preOrderTraverse (ExpressionNode p)
    {
        if (p!=null)
        {
            if(isNum(p))
                System.out.print(p.getNum()+" ");
            else
                System.out.print(p.getOpr()+" ");
            preOrderTraverse(p.getLeft());
            preOrderTraverse(p.getRight());
        }
    }

    public static void postOrderTraverse (ExpressionNode p)
    {
        if (p!=null)
        {
            postOrderTraverse(p.getLeft());
            postOrderTraverse(p.getRight());
            if(isNum(p))
                System.out.print(p.getNum()+" ");
            else
                System.out.print(p.getOpr()+" ");
        }
    }

    public static int numberCount (ExpressionNode p)
    {
        int num = 0;
        if(p!=null)
        {
            Stack<ExpressionNode> s = new Stack<ExpressionNode>();
            while (p != null || s.size() > 0)
            {
                while (p !=  null)
                {
                    s.push(p);
                    p = p.getLeft();
                }
                p = s.pop();
                if(isNum(p))
                    num++;
                p = p.getRight();
            }
        }
        return num;
    }

    public static int operatorCount (ExpressionNode p)
    {
        int opr = 0;
        if(p!=null)
        {
            Stack<ExpressionNode> s = new Stack<ExpressionNode>();
            while (p != null || s.size() > 0)
            {
                while (p !=  null)
                {
                    s.push(p);
                    p = p.getLeft();
                }
                p = s.pop();
                if(!isNum(p))
                    opr++;
                p = p.getRight();
            }
        }
        return opr;
    }

    public static double evaluate (ExpressionNode p)
    {
        double result, operand1, operand2;
        double tempN = 0;
        char tempO = ' ';

        if (p==null)
            result = 0;
        else
        {
            if (isNum(p))
                tempN = p.getNum();
            else
                tempO = p.getOpr();
            if (!isNum(p))
            {
                operand1 = evaluate(p.getLeft());
                operand2 = evaluate(p.getRight());
                result = computeTerm(tempO, operand1, operand2);

            }
            else
                result = tempN;
        }
        return result;
    }

    private static double computeTerm(char operator, double operand1, double operand2)
    {
        double result=0;

        if (operator == '+')
            result = operand1 + operand2;
        else if (operator == '-')
            result = operand1 - operand2;
        else if (operator == '*')
            result = operand1 * operand2;
        else if (operator == '/')
            result = operand1 / operand2;
        else
            result = Math.pow(operand1,operand2);

        return result;
    }


    public static boolean isNum (ExpressionNode p)
    {
        if(p.getNum()!=0 && p.getOpr()==' ')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static ExpressionNode mirror(ExpressionNode p)
    {
        ExpressionNode temp;
        if(p==null)
            return null;
        else
        {
            temp = new ExpressionNode(p.getNum(),p.getOpr(),null,null);
            temp.setLeft(mirror(p.getRight()));
            temp.setRight(mirror(p.getLeft()));
            return temp;
        }
    }
}




