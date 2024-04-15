<template>
  <div class="container">
    <h1 style="color: #1e40af;">업무 관리 캘린더</h1>

    <!-- 업무 추가 폼 -->
    <div>
      <input type="text" v-model="newTask" placeholder="새로운 업무 추가">
      <button @click="addTask">추가</button>
    </div>

    <!-- 업무 목록 -->
    <div class="mt-4">
      <div v-for="(task, index) in tasks" :key="index" class="task-item">
        <span>{{ task }}</span>
        <button @click="deleteTask(index)">삭제</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newTask: '',
      tasks: []
    };
  },
  methods: {
    addTask() {
      if (this.newTask.trim() !== '') {
        this.tasks.push(this.newTask.trim());
        this.newTask = '';
      }
    },
    deleteTask(index) {
      this.tasks.splice(index, 1);
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #c3dafe;
  padding: 8px;
  margin-top: 4px;
  border-radius: 4px;
}

.task-item button {
  background-color: #e53e3e;
  color: #fff;
  border: none;
  padding: 4px 8px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
