export interface Event {
  title: string;
  with: string;
  time: { start: string; end: string };
  color: string;
  isEditable: boolean;
  id: number;
  description?: string;
}

export interface ScheduleDTO {
  scheduleStartDate: string;
  scheduleEndDate: string;
  scheduleStartTime?: string;
  scheduleEndTime?: string;
  scheduleTitle: string;
  scheduleComment?: string | null;
  scheduleId: number;
  scheduleColor: string;
}
