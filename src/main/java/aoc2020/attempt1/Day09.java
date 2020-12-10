package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LongSummaryStatistics;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day09 implements Day {
    public final int PREAMBLE = 25;

    long[] nums;
    long invalid;
    @Override
    public void convertInput(Stream<String> stream) {
        nums = stream.mapToLong(Long::parseLong)
                .toArray();
    }
    public boolean findSum(Set<Long> sums, long target) {
        return sums.stream()
                .anyMatch(i -> sums.contains(target - i));
    }
    public long findInvalid(long[] values, int preambleLength) {
        Set<Long> sums = new HashSet<>(preambleLength);
        Arrays.stream(nums)
                .limit(preambleLength)
                .forEach(sums::add);

        for (int i = preambleLength; i<nums.length; i++) {
            if (!findSum(sums, nums[i])) return nums[i];
            sums.remove(nums[i-preambleLength]);
            sums.add(nums[i]);
        }
        return 0;
    }

    @Override //23278925
    public void part1() {
        invalid = findInvalid(nums, PREAMBLE);
        System.out.println(invalid);
    }

    @Override //4011064
    public void part2() {
        int index1 = 0;
        int index2 = 1;
        long sum = nums[index1] + nums[index2];
        while (sum != invalid) {
            if (sum < invalid) {
                index2++;
                sum+= nums[index2];
            } else {
                sum -= nums[index1];
                index1++;
            }
        }
        LongSummaryStatistics stats =
                Arrays.stream(Arrays.copyOfRange(nums,index1,index2+1))
                .summaryStatistics();

        System.out.println( (stats.getMax() + stats.getMin()));
    }
}
