package com.hzh.chapter10.binarytree;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/2 14:22
 */
public class HeroNode {

    private int no;

    private String name;

    private HeroNode left;

    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 根节点在前
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 根节点在中
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 根节点在后
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * @return
     * @param no
     */
    public HeroNode preOrderSearch(int no) {
        // 找到节点返回
        if(this.no == no) {
            return this;
        }
        HeroNode res = null;
        // 向左查找
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
        // 左边找不到(为空)则向右查找
        if (res == null && this.right != null) {
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode res = null;
        // 先找左边
        if (this.left != null) {
            res = this.left.infixOrderSearch(no);
        }
        if(res != null) return res;
        // 最左边找不到对比中间的
        if (this.no == no) {
            res = this;
        }
        if(res != null) return res;
        // 中间找不到找右边
        if (this.right != null) {
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    /**
     * 后序查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        // 先找左边
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.postOrderSearch(no);
        }
        if(res != null) return res;
        // 找右边
        if (this.right != null) {
            res = this.right.postOrderSearch(no);
        }
        if(res != null) return res;

        // 找中间
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 递归删除节点 - 所有和no相同的节点, 不是单个
     * @param no
     */
    public void deleteNode(int no) {
        // 如果左节点不为空且左节点为要删除节点, 则左节点删除
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 如果右节点不为空且右节点为要删除节点, 则右节点删除
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 左子树递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        // 右子树递归删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }
}
