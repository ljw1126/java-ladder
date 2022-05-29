package ladder.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderGame {
    private final List<Participant> participants;
    private final List<Score> scores;

    public LadderGame(List<Participant> participants, List<Score> scores) {
        validate(participants, scores);
        this.participants = participants;
        this.scores = scores;
    }

    private void validate(List<Participant> participants, List<Score> scores) {
        validateParticipantsAndScores(participants, scores);
        validateSize(participants, scores);
    }

    private void validateParticipantsAndScores(List<Participant> participants, List<Score> scores) {
        if (participants == null) {
            throw new IllegalArgumentException("전달 받은 참가자 그룹은 null 일 수 없습니다.");
        }
        if (scores == null) {
            throw new IllegalArgumentException("전달 받은 결과 점수 리스트는 null 일 수 없습니다.");
        }
    }

    private void validateSize(List<Participant> participants, List<Score> scores) {
        if (participants.size() != scores.size()) {
            throw new IllegalArgumentException(String.format("참가자와 점수 결과 갯수가 같아야 합니다. 참가자 수 : %d, 점수 결과 갯수 : %d", participants.size(), scores.size()));
        }

        int maxParticipantNameLength = maxParticipantNameLength(participants);
        int maxScoreValueLength = maxScoreValueLength(scores);
        if (maxParticipantNameLength < maxScoreValueLength) {
            throw new IllegalArgumentException(String.format("점수 결과 최대 길이는 참가자 이름 최대 길이 보다 클 수 없습니다. 현재 점수 결과 최대 길이 : %d, 현재 참가자 이름 최대 길이 : %d", maxScoreValueLength, maxParticipantNameLength));
        }
    }

    private int maxParticipantNameLength(List<Participant> participants) {
        return participants.stream().mapToInt(Participant::nameLength).max().getAsInt();
    }

    private int maxScoreValueLength(List<Score> scores) {
        return scores.stream().mapToInt(Score::scoreLength).max().getAsInt();
    }

    public LadderResult ladderResult(Ladder ladder) {
        validateLadder(ladder);
        Map<Participant, Score> results = new HashMap<>();
        for (int startPosition = 0; startPosition < participants.size(); startPosition++) {
            int finalPosition = ladder.finalPosition(startPosition);
            results.put(participants.get(startPosition), scores.get(finalPosition));
        }
        return new LadderResult(results);
    }

    private void validateLadder(Ladder ladder) {
        if (ladder == null) {
            throw new IllegalArgumentException("전달 받은 사다리는 null 일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LadderGame that = (LadderGame) o;

        if (!participants.equals(that.participants)) return false;
        return scores.equals(that.scores);
    }

    @Override
    public int hashCode() {
        int result = participants.hashCode();
        result = 31 * result + scores.hashCode();
        return result;
    }
}
