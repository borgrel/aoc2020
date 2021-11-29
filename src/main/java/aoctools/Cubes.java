package aoctools;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cubes {
    private static final char ACTIVE_STATE = '#';
    private static final char INACTIVE_STATE = '.';

    private final Set<Coord3D> activeCubes;

    public Cubes(Stream<String> initialState) {
        activeCubes = new HashSet<>();
        List<String> input = initialState.toList();
        for (int y=0; y < input.size(); y++) {
            String elements = input.get(y);
            for (int x=0; x < elements.length(); x++) {
                if (elements.charAt(x) == ACTIVE_STATE) {
                    activeCubes.add(new Coord3D(x,y,0));
                }
            }
        }
    }

    public Cubes(Cubes previousState) {
        activeCubes = previousState.getActiveCubes()
                .map(previousState::getNeighbourhood)
                .flatMap(Set::stream)
                .distinct()
                .filter(previousState::willLiveNextCycle)
                .collect(Collectors.toSet());
    }

    public boolean isActive(final Coord3D location) {
        return activeCubes.contains(location);
    }
    public Set<Coord3D> getNeighbourhood(final Coord3D location) {
        Set<Coord3D> neighbourhood = new HashSet<>();
        for (int x=-1; x<=1; x++) {
            for (int y=-1; y<=1; y++) {
                for (int z=-1; z<=1; z++) {
                    neighbourhood.add(new Coord3D(location.x()+x, location.y()+y, location.z()+z));
                }
            }
        }
        return neighbourhood;
    }
    public long getNeighbourCount(final Coord3D location) {
        return getNeighbourhood(location).stream()
                .filter(coord -> !coord.equals(location))
                .filter(this::isActive)
                .count();
    }
    public boolean willLiveNextCycle(final Coord3D location) {
        long count = getNeighbourCount(location);
        if (count == 3) return true;
        return (count == 2 && isActive(location));
    }

    public Stream<Coord3D> getActiveCubes() {
        return activeCubes.stream();
    }
    @Override
    public String toString() {
        IntSummaryStatistics xStats = getActiveCubes().mapToInt(Coord3D::x).summaryStatistics();
        IntSummaryStatistics yStats = getActiveCubes().mapToInt(Coord3D::y).summaryStatistics();
        IntSummaryStatistics zStats = getActiveCubes().mapToInt(Coord3D::z).summaryStatistics();

        StringBuilder builder = new StringBuilder();
        for (int z = zStats.getMin(); z <= zStats.getMax(); z++) {
            builder.append("z=%d%n".formatted(z));
            for (int y = yStats.getMin(); y <= yStats.getMax(); y++) {
                for (int x = xStats.getMin(); x <= xStats.getMax(); x++) {
                    boolean isAlive = isActive(new Coord3D(x,y,z));
                    builder.append(isAlive?ACTIVE_STATE:INACTIVE_STATE);
                }
                builder.append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
