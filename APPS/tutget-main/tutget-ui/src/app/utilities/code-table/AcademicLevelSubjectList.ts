import { CodeTable } from "./CodeTable";

//  export class AcademicLevelSubject{

//   public acadLvlId: string;
//   public acadSubjectIdList: string[];

// }

// export const ACADEMICLEVELSUBJECTLIST: AcademicLevelSubject[] = [
//   {
//     acadLvlId: "P1",
//     acadSubjectIdList: ["ENG", "MATH", "SCI", "CHN", "MLY", "TML"]
//   },
//   {
//     acadLvlId: "J1",
//     acadSubjectIdList: ["H1MATH", "H2MATH", "H3MATH", "H1BIO", "H2BIO", "H3BIO"]
//   },

// ]

// export const ACADEMICLEVELIDLIST: CodeTable[] = [
//   {value: "P1",
//    label: "PRIMARY ONE"
//   },
//   { value: "J1",
//     label: "JUNIOR COLLEGE ONE"
//   }
// ]

export const ACADEMICLEVELSUBJECTLIST = new Map([
  ["P1", ["ENG", "MATH", "SCI", "CHN", "MLY", "TML"]],
  ["J1", ["H1MATH", "H2MATH", "H3MATH", "H1BIO", "H2BIO", "H3BIO"]],
]);



export const ACADEMICLEVELIDLIST = new Map([
  ["P1", "PRIMARY ONE"],
  ["J1", "JUNIOR COLLEGE ONE"],

]);


export const ACADEMICSUBJECTIDLIST = new Map([
  ["ENG", "ENGLISH"],
  ["MATH", "MATHEMATICS"],
  ["SCI", "SCIENCE"],
  ["CHN", "CHINESE"],
  ["MLY", "BAHASA MELAYU"],
  ["TML", "TAMIL"],
  ["H1MATH", "H1 MATHEMATICS"],
  ["H2MATH", "H2 MATHEMATICS"],
  ["H3MATH", "H3 MATHEMATICS"],
  ["H1BIO", "H1 BIOLOGY"],
  ["H2BIO", "H2 BIOLOGY"],
  ["H3BIO", "H3 BIOLOGY"],
]);

