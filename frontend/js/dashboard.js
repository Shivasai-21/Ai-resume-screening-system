async function analyzeResume(){

const file =
document.getElementById("resumeFile").files[0];

const jobDescription =
document.getElementById("jobDescription").value;

const formData = new FormData();

formData.append("file",file);
formData.append("jobDescription",jobDescription);

const response =
await fetch(`${API_BASE_URL}/resume/analyze`,{

method:"POST",

headers:{
Authorization:
"Bearer "+
localStorage.getItem("token")
},

body:formData

});

const result =
await response.json();

document.getElementById("result").innerHTML=
`
<h4>Candidate</h4>
<p>${result.candidateName}</p>

<h4>Score</h4>
<p>${result.matchScore}%</p>

<h4>Matching Skills</h4>
<p>${result.matchingSkills}</p>

<h4>Missing Skills</h4>
<p>${result.missingSkills}</p>

<h4>Interview Questions</h4>
<p>${result.interviewQuestions}</p>
`;

}
