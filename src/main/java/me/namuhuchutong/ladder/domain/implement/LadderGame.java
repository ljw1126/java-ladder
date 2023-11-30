package me.namuhuchutong.ladder.domain.implement;

import me.namuhuchutong.ladder.domain.engine.Ladder;
import me.namuhuchutong.ladder.domain.implement.wrapper.Name;
import me.namuhuchutong.ladder.domain.implement.wrapper.OddNumber;
import me.namuhuchutong.ladder.domain.implement.wrapper.Result;
import me.namuhuchutong.ladder.domain.engine.dto.LadderResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Map.*;
import static java.util.stream.Collectors.*;

public class LadderGame implements Ladder {

    private final Rows rows;

    private final Names names;

    private final Results results;

    public LadderGame(Rows rows, Names names, Results results) {
        this.rows = rows;
        this.names = names;
        this.results = results;
    }

    @Override
    public LadderResult startLadderGame() {
        OddConvertor oddConvertor = new OddConvertor(names.size());
        LadderGameResultSequence sequence = names.stream()
                                                 .map(name -> oddConvertor.convert(names.getSequence(name)))
                                                 .map(rows::move)
                                                 .collect(collectingAndThen(toUnmodifiableList(),
                                                                            LadderGameResultSequence::new));
        return linkNameAndResult(sequence);
    }

    private LadderResult linkNameAndResult(LadderGameResultSequence sequence) {
        Map<Name, Result> nameAndResult = names.stream()
                                         .map(names::getSequence)
                                         .map(integer -> entry(names.getNthName(integer),
                                                               results.getNthResult(sequence.convert(integer))))
                                         .collect(toMap(Entry::getKey,
                                                        Entry::getValue,
                                                        (oldValue, newValue) -> oldValue,
                                                        LinkedHashMap::new));
        Map<String, String> collect = nameAndResult.keySet()
                                                   .stream()
                                                   .collect(toMap(Name::toString,
                                                                  key -> nameAndResult.get(key)
                                                                                      .toString()));
        return LadderResult.from(collect, new LadderInformation(names, rows, results));
    }

    static class OddConvertor {

        private final List<OddNumber> convertedNumbers;

        public OddConvertor(int size) {
            this.convertedNumbers = IntStream.range(0, size * 2)
                                             .map(i -> i * 2 + 1)
                                             .mapToObj(OddNumber::new)
                                             .collect(toList());
        }

        public int convert(int sequence) {
            return getOdd(sequence);
        }

        private int getOdd(int sequence) {
            return convertedNumbers.get(sequence)
                                   .getOdd();
        }
    }
}