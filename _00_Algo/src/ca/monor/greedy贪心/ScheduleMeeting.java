package ca.monor.greedy贪心;

import java.util.Arrays;
import java.util.Comparator;

public class ScheduleMeeting {
    private static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    public static int scheduleMeeting(Meeting[] meetings, int current) {
        // 按照结束时间较早的顺序排列
        Arrays.sort(meetings, new MeetingComparator());
        int sumMeeting = 0;
        for (int i = 0; i < meetings.length; i++) {

            // 找到结束时间最早的会议中，开始时间最早的会议
            if (current <= meetings[i].start) {
                sumMeeting++;
                current = meetings[i].end; // 本次会议的结束时间，做爲下次会议的开始时间，时间线向后推
            }
        }
        return sumMeeting;
    }

    private static class MeetingComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }
}
