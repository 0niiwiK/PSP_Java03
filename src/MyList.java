public class MyList<T> {
    public class Node {
        T main;
        Node nextNode;
        Node prevNode;

        public Node(T n) {
            main = n;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }

        @Override
        public String toString() {
            return "" + main.toString();
        }
    }

    Node firstNode;
    Node lastNode;
    int counter;
    Node act;

    public MyList () {
    }

    public void add (T n) {
        Node temp = new Node(n);
        if (firstNode == null) {
            temp.setNextNode(null);
            temp.setPrevNode(null);
            firstNode = temp;
            lastNode = temp;
            counter = 1;
        } else {
            firstNode.setPrevNode(temp);
            temp.setNextNode(firstNode);
            temp.setPrevNode(null);
            firstNode = temp;
            counter++;
        }
        act = temp;
    }

    public Node getAct() {
        return act;
    }

    public int getCounter() {
        return counter;
    }

    public void setAct(Node act) {
        this.act = act;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getNextNode() {
        return act.nextNode;
    }

    public void goFirst() {setAct(getFirstNode());}

    public void goLast() {setAct(getLastNode());}

    public void goNext() {setAct(getNextNode());}

    public void goPrev() {setAct(getPrevNode());}

    public void borrarNodo() {
        Node actual = getAct();
        Node prev = actual.getPrevNode();
        Node next = actual.getNextNode();
        if (isFirst() && isLast()) {
            setFirstNode(null);
            setLastNode(null);
        } else if (isFirst()) {
            next.setPrevNode(null);
            setFirstNode(next);
        } else if (isLast()) {
            prev.setNextNode(null);
            setLastNode(prev);
        } else {
            prev.setNextNode(next);
            next.setPrevNode(prev);
        }
    }

    public Node getPrevNode() {
        return act.prevNode;
    }

    public Node getLastNode() {
        return lastNode;
    }

    public void setFirstNode(Node n) {
        firstNode = n;
    }

    public void setLastNode(Node n) {
        lastNode = n;
    }

    public boolean isLast() {
        return getAct() == getLastNode();
    }

    public boolean isFirst() {
        return getAct() == getFirstNode();
    }

    public Node getElement(int x) {
        if (x <= this.counter) {
            this.setAct(getFirstNode());
            for (int i = 0; i < x; i++) {
                this.setAct(getNextNode());
            }
            return getAct();
        } else
            return null;
    }
}
