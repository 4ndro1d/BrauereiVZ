package eu.alldev.brauereivz.listener;

import java.util.List;

import eu.alldev.brauereivz.model.Relation;

public interface RelationChangedListener {

    void relationsChanged(List<Relation> entities);
}
