  <script setup lang="ts">
  import { ref, onMounted, shallowRef } from 'vue';
  import { format } from 'date-fns'
  import axios, {setClientHeaders} from '@/utils/axios'; // axios import
  import { useRouter } from 'vue-router';

  const token: string | null = localStorage.getItem('token');
  const baseUrl = `${import.meta.env.VITE_API_URL}`;
  const router = useRouter();


  const deleteItem = async () => {
    try {
      const noticeId = props.selectedDetail.noticeId;
      console.log(noticeId);
      const response = await axios.delete(`${baseUrl}/login/notice/delete/${noticeId}`);
      alert("삭제성공");
      location.reload();

    } catch (error) {
      console.error(error); 
    }
  }
  const editItem = async () =>{
  }
    
  const props = defineProps({
    selectedDetail: Object || Array
  })
  
  const downloadFile = async (filePath: string) => {
    try {
      if (filePath) {
        // 파일 다운로드
        console.log("앙")
        const token = localStorage.getItem('token'); // 로컬 스토리지에서 토큰을 가져옴
        if (token) {
            const headers = {
                'Authorization': `Bearer ${token}`
            };
            // 헤더에 토큰을 추가하여 요청을 보냄
            console.log(headers)
            await axios.post(`${baseUrl}/login/notice/download/{fileName}`, '', { headers })
            .then((response) => {
              function replaceAll (str: string, searchStr: string, replaceStr: string) {
              return str.split(searchStr).join(replaceStr)
            }
              const url = window.URL.createObjectURL(new Blob([response.data], { type: 'image/png' }))
              const link = document.createElement('a')
              link.href = url
              const filename = replaceAll(decodeURI(response.headers.filename), '+', ' ')
              link.setAttribute('download', filename)
              document.body.appendChild(link)
              link.click()
              })
        }
      }
    } catch(error){
        alert(error);
    }
  }
  </script>

  <template>
    <div class="topbarMail d-flex ga-4 align-center w-100">
      <!-- 뒤로가기 버튼 -->
      <v-btn icon flat @click="$emit('toggleDetail')" size="small"><ChevronLeftIcon size="18" /></v-btn>
      <!-- 이메일 이름 -->
      <div class="d-flex align-center ga-4 w-100">
        <div>
          <h4 class="text-h4 mb-n1">noticeAdmin</h4>
          <small>From: admin@test.com</small>
        </div>
      </div>
      <!-- 오른쪽 끝에 날짜 -->
      <div class="ml-auto text-subtitle-2 text-medium-emphasis">{{ format(new Date(selectedDetail?.createdAt!), 'd MMM') }}</div>
    </div>
    <div class="d-flex align-center ga-2 mt-3">
      <h3 class="text-h3 py-4 mr-auto">{{ selectedDetail?.title }}</h3>
    </div>
    <div class="py-4 text-subtitle-1 font-weight-light">
      {{ selectedDetail?.contents }}
      <br />
    </div>

    <!-- 전체 컨테이너 -->
    <div class="d-flex flex-column justify-content-between" style="height: 100%;">
      <!-- 여기에 다른 내용 추가 가능 -->
      <div class="align-self-end">
        <v-button class="edit-button" @click="editItem">수정하기</v-button>
        <v-button class="delete-button" @click="deleteItem">삭제하기</v-button>
      </div>
    </div>
    <div>
      <!-- 파일명을 클릭 가능한 링크로 만듭니다 -->
      <span style="cursor: pointer; text-decoration: underline;" @click="downloadFile(selectedDetail?.filePath)">
        {{ selectedDetail?.filePath }}
      </span>
    </div>
  </template>

  <style>
  .delete-button, .edit-button {
    margin-left: 10px;
    background-color: #5938de; 
    color: white; 
    border-radius: 10px; 
    padding: 10px 20px; 
    border: none; 
    cursor: pointer; 
    font-size: 16px;
  }

  /* 버튼에 마우스를 올렸을 때의 스타일 */
  .delete-button:hover, .edit-button:hover {
    background-color: #766ee6; 
  }

  .edit-button {
    background-color: #4CAF50; /* 수정하기 버튼을 위한 다른 색상 */
  }

  .edit-button:hover {
    background-color: #66bb6a;
  }
  </style>