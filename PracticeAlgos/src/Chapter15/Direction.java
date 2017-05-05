package Chapter15;

public enum Direction {
up, down, left, right;

public Direction next;

static {
    right.next = down;
    down.next = left;
    left.next = up;
    up.next = right;
}





}
