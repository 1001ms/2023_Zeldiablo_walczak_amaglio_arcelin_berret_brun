import gameLaby.laby.Echappatoire;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.ElasticCharAppender;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEchappatoire {

    @Test
    public void test_ConstructeurEchappatoire() {
        Echappatoire e = new Echappatoire();
        ArrayList<int[]> eNv1 = new ArrayList<>(9);
        ArrayList<int[]> eNv2 = new ArrayList<>(9);
        ArrayList<int[]> eNv3 = new ArrayList<>(9);
        for (int i = 0; i<9; i++) eNv1.add(new int[]{-1,-1,-1});
        for (int i = 0; i<9; i++) eNv2.add(new int[]{-1,-1,-1});
        for (int i = 0; i<9; i++) eNv3.add(new int[]{-1,-1,-1});
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<3; j++) assertEquals(eNv1.get(i)[j], e.getEscpNv(0).get(i)[j]);
        }
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<3; j++) assertEquals(eNv2.get(i)[j], e.getEscpNv(1).get(i)[j]);
        }
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<3; j++) assertEquals(eNv3.get(i)[j], e.getEscpNv(2).get(i)[j]);
        }
    }

    @Test
    public void test_typeToIndex() {
        Echappatoire e = new Echappatoire();
        int b = 1;
        assertEquals(b,e.typeToIndex('B'));
    }

    @Test
    public void test_indexToType() {
        Echappatoire e = new Echappatoire();
        char b = 'B';
        assertEquals(b,e.indexToType(1));
    }

    @Test
    public void test_determinerNiv() {
        Echappatoire e = new Echappatoire();
        e.determinerNiv(1,1,2,0,'B');
        assertTrue(e.getEscpNv(0).get(1)[0] == 1);
    }

    @Test
    public void test_add() {
        Echappatoire e = new Echappatoire();
    }
}
