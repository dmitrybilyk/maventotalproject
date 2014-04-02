package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 24.03.13
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
class ListsMain {
    public static void main(String[] args) {
        def list = [3, new Date(), 'Jan']
        assert list + list == list * 2

        assert [1,2,3,4]           == (1..4)
        assert [1,2,3] + [1]       == [1,2,3,1]
        assert [1,2,3] << 1        == [1,2,3,1]
        assert [1,2,3,1] - [1]     == [2,3]
        assert [1,2,3] * 2         == [1,2,3,1,2,3]
        assert [1,[2,3]].flatten() == [1,2,3]
        assert [1,2,3].reverse()   == [3,2,1]
        assert [1,2,3].disjoint([4,5,6])
        assert [1,2,3].intersect([4,3,1]) == [3,1]
        assert [1,2,3].collect{ it+3 }    == [4,5,6]
        assert [1,2,3,1].unique().size()  == 3
        assert [1,2,3,1].count(1)  == 2
        assert [1,2,3,4].min()     == 1
        assert [1,2,3,4].max()     == 4
        assert [1,2,3,4].sum()     == 10
        assert [4,2,1,3].sort()    == [1,2,3,4]
        assert [4,2,1,3].findAll{ it%2 == 0 } == [4,2]
    }
}
