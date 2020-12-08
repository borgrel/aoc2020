package aoc2020.attempt1;

import aoc2020.Day;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day08 implements Day {
    enum Instruc {
            /* acc increases or decreases a single global value called the accumulator by the value given in the argument.
               For example, acc +7 would increase the accumulator by 7. The accumulator starts at 0.
               After an acc instruction, the instruction immediately below it is executed next.*/
        ACC (Program::accumulate),
            /* jmp jumps to a new instruction relative to itself. The next instruction to execute is found using the
               argument as an offset from the jmp instruction; for example, jmp +2 would skip the next instruction,
               jmp +1 would continue to the instruction immediately below it, and jmp -20 would cause the instruction
               20 lines above to be executed next.*/
        JMP (Program::shiftIndex),
            // nop stands for No Operation - it does nothing. The instruction immediately below it is executed next.
        NOP (program -> {});

        String key;
        Consumer<Program> instruction;
        Instruc(Consumer<Program> instruction) {
            this.instruction = instruction;
            key = this.name().toLowerCase();
        }
        public void exec(Program program) {
            instruction.accept(program);
        }
        static Instruc parseInstruction(String value) {
            for (Instruc selected: Instruc.values()) {
                if (value.equals(selected.key))
                    return selected;
            }
            throw new IllegalArgumentException("This ENUM does not include the value: " + value);
        }
    }
    static class Register {
        private final Instruc instruction;
        private final int value;

        Register(Instruc instruction,int value) {
            this.instruction = instruction;
            this.value = value;
        }
        public int value() {
            return value;
        }
        public Instruc instruction() {
            return instruction;
        }
        public void exec(Program program) {
            instruction.exec(program);
        }
        @Override
        public String toString() {
            return instruction + "( " + value + " )";
        }
        static Register parse(String[] line) {
            Instruc instruc = Instruc.parseInstruction(line[0]);
            int value = Integer.parseInt(line[1]);
            return new Register(instruc,value);
        }
        static Register swapInstruction(Register register) {
            switch (register.instruction()) {
                case JMP: return new Register(Instruc.NOP, register.value());
                case NOP: return new Register(Instruc.JMP, register.value());
                default: throw new IllegalArgumentException("Instruction for swapping is not JMP or NOP: "
                        + register.instruction());
            }
        }
    }
    static class Program {
        private int accumulator;
        private int index;
        private Register[] instructions;

        Program(Register[] instructions) {
            this.instructions = instructions;
            accumulator = 0;
            index = 0;
        }
        public int index() {
            return index;
        }
        public int accumulator() {
            return accumulator;
        }
        public int size() {
            return instructions.length;
        }
        public Instruc currentInstruction() {
            return instructions[index].instruction();
        }
        public void accumulate() {
            accumulator+=instructions[index].value();
        }
        public void shiftIndex() {
            index+=instructions[index].value()-1;
        }
        public void performInstruc() {
            if (completed()) return;
            instructions[index].exec(this);
            index++;
        }
        public boolean completed() {
            return index >= instructions.length;
        }
    }
    Register[] instructions;
    Program program;
    Deque<Integer> locations;
    @Override
    public void convertInput(Stream<String> stream) {
        Pattern asSpaces = Pattern.compile(" ");
        instructions = stream.map(asSpaces::split)
                .map(Register::parse)
                .toArray(Register[]::new);

        program = new Program(instructions);
    }

    @Override
    public void part1() {
        attemptRun(program,true);
        System.out.println(program.accumulator());
    }
    public boolean attemptRun(Program program,boolean firstRun) {
        if (firstRun) locations = new ArrayDeque<>();
        int[] repeated = new int[program.size()];
        while (!program.completed()) {
            if (repeated[program.index()] > 0) return false;
            repeated[program.index()]++;
            if (firstRun && (program.currentInstruction() == Instruc.JMP
                    || program.currentInstruction() == Instruc.NOP)) {
                locations.add(program.index());
            }
            program.performInstruc();
        }
        return true;
    }

    @Override
    public void part2() {
        Program test;
        do {
            if (locations.isEmpty()) {
                System.out.println("No available locations to alter");
                return;
            }
            int changeIndex = locations.removeLast();
            Register oldRegister = instructions[changeIndex];
            Register[] temp = Arrays.copyOf(instructions, instructions.length);
            temp[changeIndex] = Register.swapInstruction(oldRegister);
            test = new Program(temp);
            attemptRun(test, false);
        } while (!test.completed());
        System.out.println(test.accumulator());
    }
}
