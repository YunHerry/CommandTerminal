package indi.yunherry.model.dto;

import indi.yunherry.annotation.Aggregate;
import indi.yunherry.resolve.AbstractResolver;
import indi.yunherry.resolve.ResolversIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author h3209
 */
public class Resolvers implements Aggregate {
    private List<AbstractResolver> resolvers;

    public Resolvers(AbstractResolver[] resolvers) {
        List<AbstractResolver> resolverList = Arrays.asList(resolvers);
    }
    protected AbstractResolver getResolver(int index) {
        return this.resolvers.get(index);
    }
    protected boolean addResolver(AbstractResolver resolver) {
        resolvers.add(resolver);
        return true;
    }
    public List<AbstractResolver> getResolvers() {
        return this.resolvers;
    }
    @Override
    public Iterator<?> iterator() {
        return new ResolversIterator(this);
    }
}
