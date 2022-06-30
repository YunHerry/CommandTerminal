package indi.yunherry.resolve;

import indi.yunherry.model.dto.Resolvers;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author YunHerry
 */
public class ResolversIterator implements Iterator<AbstractResolver> {
    private List<AbstractResolver> resolvers;
    private int index = 0;
    public ResolversIterator(Resolvers resolvers) {
        this.resolvers = resolvers.getResolvers();
    }
    @Override
    public boolean hasNext() {
        if (this.resolvers.size() > index) {
            return true;
        }
        return false;
    }

    @Override
    public AbstractResolver next() {
        AbstractResolver abstractResolver = Objects.requireNonNull(resolvers.get(index));
        index++;
        return abstractResolver;
    }
}
