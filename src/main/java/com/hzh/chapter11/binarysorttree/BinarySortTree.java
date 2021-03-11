package com.hzh.chapter11.binarysorttree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/10 22:00
 */
public class BinarySortTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 删除节点
     * @param value
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 获取需要删除的节点targetNode
        Node targetNode = search(value);
        if(targetNode == null) { // 没有找到返回空
            return;
        }
        // 如果找到了但是当前只有一个根节点, 也就是根节点为目标节点, 根节点置空
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        // 继续找targetNode的父节点
        Node parentNode = searchParent(value);

        // 此时有三种情况*******

        // ① 如果要删除的节点是叶子节点(也就是有父节点,而且当前节点的左右节点均为空)
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            // 判断是parent的左子节点还是右子节点, 主要要先判空, 可能只有一个子节点
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);  // 这边就剩下右子节点了, 不可能右也是空的, 那样的话target就是root了
            }
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) { // ② 删除有两颗子树的节点
            // 获取该节点右树最小值
            int minVal = delRightTreeMin(targetNode.getRight());
            // 替换该节点的值
            targetNode.setValue(minVal);

        } else { // ③ 删除只有一颗子树的节点
            // 如果targetNode有左子节点, targetNode的左子节点替代targetNode的位置
            // 如果targetNode有右子节点, targetNode的右子节点替代targetNode的位置
            if (targetNode.getLeft() != null) {
                if (parentNode.getLeft().getValue() == value) { // 如果targetNode有左子节点
                    // 如果targetNode是parent的左子节点, 则targetNode的左子节点替代targetNode的位置即parentNode的左子节点
                    parentNode.setLeft(targetNode.getLeft());
                } else {
                    // 如果targetNode是parent的右子节点, 则targetNode的左子节点替代targetNode的位置即parentNode的右子节点
                    parentNode.setRight(targetNode.getLeft());
                }
            } else {  // 如果targetNode有右子节点
                // 如果targetNode是parent的右子节点
                if (parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(targetNode.getRight());
                } else {
                    parentNode.setRight(targetNode.getRight());
                }
            }
        }


    }

    /**
     * 获取当前节点右子树的最小值并删除该节点
     * @param node
     * @return
     */
    private int delRightTreeMin(Node node) {
        Node temp = node;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        // 删除该最小节点
        delNode(temp.getValue());
        return temp.getValue();
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            throw new RuntimeException("树空");
        }
        root.infixOrder();
    }

}
