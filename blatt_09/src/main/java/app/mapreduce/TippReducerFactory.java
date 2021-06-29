package app.mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.othr.vs.xml.Veranstaltung;

import java.util.ArrayList;
import java.util.List;

public class TippReducerFactory implements ReducerFactory<String, Veranstaltung, List<Veranstaltung>> {

    @Override
    public Reducer<Veranstaltung, List<Veranstaltung>> newReducer(String s) {
        return new TippReducer();
    }

    private class TippReducer extends Reducer<Veranstaltung, List<Veranstaltung>> {

        private List<Veranstaltung> veranstaltungen = new ArrayList<>();

        @Override
        public void reduce(Veranstaltung veranstaltung) {
            veranstaltungen.add(veranstaltung);
        }

        @Override
        public List<Veranstaltung> finalizeReduce() {
            return veranstaltungen;
        }
    }
}
