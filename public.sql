/*
 Navicat PostgreSQL Data Transfer

 Source Server         : yuvi.cuytbdxg065v.ap-south-1.rds.amazonaws.com
 Source Server Type    : PostgreSQL
 Source Server Version : 150005 (150005)
 Source Host           : yuvi.cuytbdxg065v.ap-south-1.rds.amazonaws.com:5432
 Source Catalog        : qms111
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 150005 (150005)
 File Encoding         : 65001

 Date: 05/05/2024 12:57:26
*/


-- ----------------------------
-- Type structure for action_types
-- ----------------------------
DROP TYPE IF EXISTS "public"."action_types";
CREATE TYPE "public"."action_types" AS ENUM (
  'CORRECTIVE',
  'PREVENTIVE'
);
ALTER TYPE "public"."action_types" OWNER TO "postgres";
COMMENT ON TYPE "public"."action_types" IS 'Differentiates between corrective actions (addressing current problems) and preventive actions (to prevent future issues).';

-- ----------------------------
-- Type structure for capa_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."capa_status";
CREATE TYPE "public"."capa_status" AS ENUM (
  'OPEN',
  'IN_PROGRESS',
  'COMPLETED',
  'CLOSED'
);
ALTER TYPE "public"."capa_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."capa_status" IS 'Enumeration of statuses for CAPA records, including Open, In Progress, Completed, Closed.';

-- ----------------------------
-- Type structure for change_control_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."change_control_status";
CREATE TYPE "public"."change_control_status" AS ENUM (
  'DRAFT',
  'PENDING_APPROVAL',
  'APPROVED',
  'IMPLEMENTED',
  'CANCELLED'
);
ALTER TYPE "public"."change_control_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."change_control_status" IS 'Describes the stages of change control from initiation to cancellation or completion.';

-- ----------------------------
-- Type structure for deviation_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."deviation_status";
CREATE TYPE "public"."deviation_status" AS ENUM (
  'OPEN',
  'UNDER_REVIEW',
  'CLOSED',
  'REJECTED',
  'PENDING_DEPARTMENT_REVIEW',
  'ONGOING_DEPARTMENT_REVIEW',
  'PENDING_CFT_REVIEW',
  'UNDER_CFT_REVIEW',
  'PENDING_QA_REVIEW',
  'APPROVED_BY_QA',
  'PENDING_FINAL_APPROVAL'
);
ALTER TYPE "public"."deviation_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."deviation_status" IS 'Tracks the lifecycle status of a deviation from open to closure or rejection.';

-- ----------------------------
-- Type structure for document_types
-- ----------------------------
DROP TYPE IF EXISTS "public"."document_types";
CREATE TYPE "public"."document_types" AS ENUM (
  'CHANGE_CONTROL',
  'CAPA_FORM',
  'DEVIATION_REPORT',
  'TRAINING_RECORD'
);
ALTER TYPE "public"."document_types" OWNER TO "postgres";
COMMENT ON TYPE "public"."document_types" IS 'Categories of documents managed within the system, ensuring appropriate handling for each type.';

-- ----------------------------
-- Type structure for equipment_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."equipment_status";
CREATE TYPE "public"."equipment_status" AS ENUM (
  'OPERATIONAL',
  'UNDER_MAINTENANCE',
  'OUT_OF_SERVICE',
  'DECOMMISSIONED'
);
ALTER TYPE "public"."equipment_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."equipment_status" IS 'Enumeration of statuses for equipment such as Operational, Under Maintenance, Out of Service, Decommissioned.';

-- ----------------------------
-- Type structure for event_related_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."event_related_enum";
CREATE TYPE "public"."event_related_enum" AS ENUM (
  'PRODUCT',
  'MATERIAL',
  'EQUIPMENT',
  'DOCUMENT',
  'SOFTWARE',
  'OTHERS'
);
ALTER TYPE "public"."event_related_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."event_related_enum" IS 'Enumeration of possible types of events related to deviations such as product, material, equipment, etc.';

-- ----------------------------
-- Type structure for notification_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."notification_status";
CREATE TYPE "public"."notification_status" AS ENUM (
  'UNREAD',
  'READ',
  'ARCHIVED'
);
ALTER TYPE "public"."notification_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."notification_status" IS 'Manages the state of user notifications within the system, from unread to archived.';

-- ----------------------------
-- Type structure for product_category
-- ----------------------------
DROP TYPE IF EXISTS "public"."product_category";
CREATE TYPE "public"."product_category" AS ENUM (
  'PHARMACEUTICAL',
  'BIOTECHNOLOGY',
  'MEDICAL_DEVICE',
  'HEALTHCARE',
  'OTHER'
);
ALTER TYPE "public"."product_category" OWNER TO "postgres";
COMMENT ON TYPE "public"."product_category" IS 'Enumeration of product categories such as Pharmaceutical, Biotechnology, Medical Device, Healthcare, and Others.';

-- ----------------------------
-- Type structure for product_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."product_status";
CREATE TYPE "public"."product_status" AS ENUM (
  'ACTIVE',
  'DISCONTINUED',
  'DEVELOPMENT',
  'RECALLED'
);
ALTER TYPE "public"."product_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."product_status" IS 'Enumeration of possible statuses for products such as Active, Discontinued, in Development, or Recalled.';

-- ----------------------------
-- Type structure for review_decision_types
-- ----------------------------
DROP TYPE IF EXISTS "public"."review_decision_types";
CREATE TYPE "public"."review_decision_types" AS ENUM (
  'APPROVE',
  'REJECT',
  'REQUIRE_REVISION'
);
ALTER TYPE "public"."review_decision_types" OWNER TO "postgres";
COMMENT ON TYPE "public"."review_decision_types" IS 'Possible outcomes of a review process for deviations or change controls.';

-- ----------------------------
-- Type structure for role_names
-- ----------------------------
DROP TYPE IF EXISTS "public"."role_names";
CREATE TYPE "public"."role_names" AS ENUM (
  'ADMIN',
  'USER',
  'SUPERVISOR',
  'AUDITOR',
  'QA',
  'PRODUCTION'
);
ALTER TYPE "public"."role_names" OWNER TO "postgres";
COMMENT ON TYPE "public"."role_names" IS 'Defines roles within the system to control access and permissions.';

-- ----------------------------
-- Type structure for batch_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."batch_status";
CREATE TYPE "public"."batch_status" AS ENUM (
  'ACTIVE',
  'IN_REVIEW',
  'EXPIRED',
  'RECALLED'
);
ALTER TYPE "public"."batch_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."batch_status" IS 'Enumeration of possible statuses for batches, including Active, In Review, Expired, and Recalled.';

-- ----------------------------
-- Type structure for capa_action_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."capa_action_status";
CREATE TYPE "public"."capa_action_status" AS ENUM (
  'PENDING',
  'IN_PROGRESS',
  'COMPLETED'
);
ALTER TYPE "public"."capa_action_status" OWNER TO "postgres";

-- ----------------------------
-- Type structure for capa_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."capa_type";
CREATE TYPE "public"."capa_type" AS ENUM (
  'CORRECTIVE',
  'PREVENTIVE'
);
ALTER TYPE "public"."capa_type" OWNER TO "postgres";
COMMENT ON TYPE "public"."capa_type" IS 'Enumeration of types of CAPA actions, including Corrective and Preventive.';

-- ----------------------------
-- Type structure for equipment_category
-- ----------------------------
DROP TYPE IF EXISTS "public"."equipment_category";
CREATE TYPE "public"."equipment_category" AS ENUM (
  'PRODUCTION',
  'LABORATORY',
  'MAINTENANCE',
  'SAFETY',
  'OTHER'
);
ALTER TYPE "public"."equipment_category" OWNER TO "postgres";
COMMENT ON TYPE "public"."equipment_category" IS 'Enumeration of types of equipment such as Production, Laboratory, Maintenance, Safety, Other.';

-- ----------------------------
-- Type structure for follow_up_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."follow_up_status";
CREATE TYPE "public"."follow_up_status" AS ENUM (
  'SCHEDULED',
  'COMPLETED',
  'DELAYED'
);
ALTER TYPE "public"."follow_up_status" OWNER TO "postgres";

-- ----------------------------
-- Type structure for investigation_methodology_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."investigation_methodology_enum";
CREATE TYPE "public"."investigation_methodology_enum" AS ENUM (
  'FIVE_WHY',
  'FISHBONE',
  'ADKOM',
  'DMAIC'
);
ALTER TYPE "public"."investigation_methodology_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."investigation_methodology_enum" IS 'Enumeration of possible methodologies used in investigations such as 5 Why, Fishbone (Ishikawa), ADKOM, and DMAIC.';

-- ----------------------------
-- Type structure for investigation_status_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."investigation_status_enum";
CREATE TYPE "public"."investigation_status_enum" AS ENUM (
  'OPEN',
  'CLOSED',
  'ON_HOLD'
);
ALTER TYPE "public"."investigation_status_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."investigation_status_enum" IS 'Enumeration of possible statuses for an investigation, such as open, closed, or on hold.';

-- ----------------------------
-- Type structure for material_category
-- ----------------------------
DROP TYPE IF EXISTS "public"."material_category";
CREATE TYPE "public"."material_category" AS ENUM (
  'RAW_MATERIAL',
  'PACKAGING',
  'CHEMICAL',
  'OTHER'
);
ALTER TYPE "public"."material_category" OWNER TO "postgres";
COMMENT ON TYPE "public"."material_category" IS 'Enumeration of types of materials such as Raw Material, Packaging, Chemical, Other.';

-- ----------------------------
-- Type structure for material_status
-- ----------------------------
DROP TYPE IF EXISTS "public"."material_status";
CREATE TYPE "public"."material_status" AS ENUM (
  'IN_STOCK',
  'LOW_STOCK',
  'OUT_OF_STOCK',
  'DISCONTINUED'
);
ALTER TYPE "public"."material_status" OWNER TO "postgres";
COMMENT ON TYPE "public"."material_status" IS 'Enumeration of statuses for materials such as In Stock, Low Stock, Out of Stock, Discontinued.';

-- ----------------------------
-- Type structure for risk_factor_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."risk_factor_type";
CREATE TYPE "public"."risk_factor_type" AS ENUM (
  'PROBABILITY',
  'PROCESS_STEPS',
  'MICROBIOLOGICAL',
  'CROSS_CONTAMINATION',
  'PRODUCT_IMPACT',
  'INVESTIGATION_COMPLEXITY',
  'QUALITY_CRITICAL'
);
ALTER TYPE "public"."risk_factor_type" OWNER TO "postgres";

-- ----------------------------
-- Sequence structure for adkom_steps_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."adkom_steps_id_seq";
CREATE SEQUENCE "public"."adkom_steps_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for batches_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."batches_id_seq";
CREATE SEQUENCE "public"."batches_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for capa_actions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."capa_actions_id_seq";
CREATE SEQUENCE "public"."capa_actions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for capa_files_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."capa_files_id_seq";
CREATE SEQUENCE "public"."capa_files_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for capa_follow_ups_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."capa_follow_ups_id_seq";
CREATE SEQUENCE "public"."capa_follow_ups_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for capa_risk_assessments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."capa_risk_assessments_id_seq";
CREATE SEQUENCE "public"."capa_risk_assessments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for capas_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."capas_id_seq";
CREATE SEQUENCE "public"."capas_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for comments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."comments_id_seq";
CREATE SEQUENCE "public"."comments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for departments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."departments_id_seq";
CREATE SEQUENCE "public"."departments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for deviation_closure_approvals_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."deviation_closure_approvals_id_seq";
CREATE SEQUENCE "public"."deviation_closure_approvals_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for deviation_reviewers_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."deviation_reviewers_id_seq";
CREATE SEQUENCE "public"."deviation_reviewers_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for deviations_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."deviations_id_seq";
CREATE SEQUENCE "public"."deviations_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for dmaic_steps_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."dmaic_steps_id_seq";
CREATE SEQUENCE "public"."dmaic_steps_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for equipments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."equipments_id_seq";
CREATE SEQUENCE "public"."equipments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for fishbone_steps_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."fishbone_steps_id_seq";
CREATE SEQUENCE "public"."fishbone_steps_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for five_whys_steps_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."five_whys_steps_id_seq";
CREATE SEQUENCE "public"."five_whys_steps_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for investigations_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."investigations_id_seq";
CREATE SEQUENCE "public"."investigations_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for materials_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."materials_id_seq";
CREATE SEQUENCE "public"."materials_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for notifications_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."notifications_id_seq";
CREATE SEQUENCE "public"."notifications_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for products_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."products_id_seq";
CREATE SEQUENCE "public"."products_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for qa_deviation_risk_assessments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."qa_deviation_risk_assessments_id_seq";
CREATE SEQUENCE "public"."qa_deviation_risk_assessments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."roles_id_seq";
CREATE SEQUENCE "public"."roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for adkom_steps
-- ----------------------------
DROP TABLE IF EXISTS "public"."adkom_steps";
CREATE TABLE "public"."adkom_steps" (
  "id" int4 NOT NULL DEFAULT nextval('adkom_steps_id_seq'::regclass),
  "investigation_id" int4 NOT NULL,
  "step" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "assessment" text COLLATE "pg_catalog"."default",
  "result" bool,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."adkom_steps"."step" IS 'The ADKOM step being evaluated (e.g., Ability, Direction, Knowledge, Opportunity, Motivation).';
COMMENT ON COLUMN "public"."adkom_steps"."assessment" IS 'Description of the assessment carried out for the ADKOM step.';
COMMENT ON COLUMN "public"."adkom_steps"."result" IS 'Result of the assessment: true if the criteria are met, false otherwise.';
COMMENT ON TABLE "public"."adkom_steps" IS 'Tracks each step of an ADKOM analysis within an investigation, focusing on Ability, Direction, Knowledge, Opportunity, and Motivation.';

-- ----------------------------
-- Table structure for batches
-- ----------------------------
DROP TABLE IF EXISTS "public"."batches";
CREATE TABLE "public"."batches" (
  "id" int4 NOT NULL DEFAULT nextval('batches_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "batch_number" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "status" "public"."batch_status" NOT NULL DEFAULT 'ACTIVE'::batch_status,
  "manufacture_date" date,
  "expiry_date" date,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."batches"."id" IS 'The primary key identifier for a batch.';
COMMENT ON COLUMN "public"."batches"."product_id" IS 'Foreign key referencing products, linking the batch to a specific product.';
COMMENT ON COLUMN "public"."batches"."batch_number" IS 'The unique identifier or number of the batch, as assigned during production.';
COMMENT ON COLUMN "public"."batches"."status" IS 'The status of the batch, e.g., ACTIVE, IN_REVIEW, EXPIRED.';
COMMENT ON COLUMN "public"."batches"."manufacture_date" IS 'The date on which the batch was manufactured.';
COMMENT ON COLUMN "public"."batches"."expiry_date" IS 'The expiry date of the batch, determining until when the product can be used or sold.';
COMMENT ON COLUMN "public"."batches"."created_at" IS 'The timestamp when the batch record was created.';
COMMENT ON COLUMN "public"."batches"."updated_at" IS 'The timestamp when the batch record was last updated.';
COMMENT ON TABLE "public"."batches" IS 'Stores information about production batches of products, used for tracking and compliance purposes.';

-- ----------------------------
-- Table structure for capa_actions
-- ----------------------------
DROP TABLE IF EXISTS "public"."capa_actions";
CREATE TABLE "public"."capa_actions" (
  "id" int4 NOT NULL DEFAULT nextval('capa_actions_id_seq'::regclass),
  "capa_id" int4 NOT NULL,
  "action_description" text COLLATE "pg_catalog"."default" NOT NULL,
  "actionee_id" int4,
  "due_date" date,
  "status" "public"."capa_action_status" NOT NULL DEFAULT 'PENDING'::capa_action_status,
  "completion_date" date,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."capa_actions"."id" IS 'The primary key identifier for a CAPA action.';
COMMENT ON COLUMN "public"."capa_actions"."capa_id" IS 'Foreign key that links to the CAPA record this action is part of.';
COMMENT ON COLUMN "public"."capa_actions"."action_description" IS 'Description of the action to be taken.';
COMMENT ON COLUMN "public"."capa_actions"."actionee_id" IS 'Foreign key linking to the user responsible for carrying out the action.';
COMMENT ON COLUMN "public"."capa_actions"."due_date" IS 'The due date by which the action should be completed.';
COMMENT ON COLUMN "public"."capa_actions"."status" IS 'The current status of the action (e.g., Pending, In Progress, Completed).';
COMMENT ON COLUMN "public"."capa_actions"."completion_date" IS 'The actual date on which the action was completed.';
COMMENT ON COLUMN "public"."capa_actions"."created_at" IS 'The timestamp when the CAPA action record was created.';
COMMENT ON COLUMN "public"."capa_actions"."updated_at" IS 'The timestamp when the CAPA action record was last updated.';
COMMENT ON TABLE "public"."capa_actions" IS 'Stores specific actions taken or to be taken under each CAPA record, detailing what needs to be done, by whom, and by when.';

-- ----------------------------
-- Table structure for capa_files
-- ----------------------------
DROP TABLE IF EXISTS "public"."capa_files";
CREATE TABLE "public"."capa_files" (
  "id" int4 NOT NULL DEFAULT nextval('capa_files_id_seq'::regclass),
  "capa_id" int4 NOT NULL,
  "file_path" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "file_description" text COLLATE "pg_catalog"."default",
  "uploaded_by" int4,
  "uploaded_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."capa_files"."id" IS 'The primary key identifier for a CAPA file.';
COMMENT ON COLUMN "public"."capa_files"."capa_id" IS 'Foreign key that links to the CAPA record this file is associated with.';
COMMENT ON COLUMN "public"."capa_files"."file_path" IS 'The path to the file stored, typically pointing to a secure file storage location.';
COMMENT ON COLUMN "public"."capa_files"."file_description" IS 'A description of the file, explaining its relevance or contents.';
COMMENT ON COLUMN "public"."capa_files"."uploaded_by" IS 'The ID of the user who uploaded the file, linking back to the users table.';
COMMENT ON COLUMN "public"."capa_files"."uploaded_at" IS 'The timestamp when the file was uploaded.';
COMMENT ON COLUMN "public"."capa_files"."created_at" IS 'The timestamp when the CAPA file record was created.';
COMMENT ON COLUMN "public"."capa_files"."updated_at" IS 'The timestamp when the CAPA file record was last updated.';
COMMENT ON TABLE "public"."capa_files" IS 'Stores files or documents related to CAPAs, such as evidence of completed actions, supporting materials for assessments, or any documentation that needs to be archived for regulatory compliance.';

-- ----------------------------
-- Table structure for capa_follow_ups
-- ----------------------------
DROP TABLE IF EXISTS "public"."capa_follow_ups";
CREATE TABLE "public"."capa_follow_ups" (
  "id" int4 NOT NULL DEFAULT nextval('capa_follow_ups_id_seq'::regclass),
  "capa_id" int4 NOT NULL,
  "follow_up_date" date NOT NULL,
  "follow_up_description" text COLLATE "pg_catalog"."default",
  "status" "public"."follow_up_status" NOT NULL DEFAULT 'SCHEDULED'::follow_up_status,
  "outcome" text COLLATE "pg_catalog"."default",
  "conducted_by" int4,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."capa_follow_ups"."id" IS 'The primary key identifier for a CAPA follow-up activity.';
COMMENT ON COLUMN "public"."capa_follow_ups"."capa_id" IS 'Foreign key that links to the corresponding CAPA record.';
COMMENT ON COLUMN "public"."capa_follow_ups"."follow_up_date" IS 'The scheduled date for the follow-up activity.';
COMMENT ON COLUMN "public"."capa_follow_ups"."follow_up_description" IS 'Description of what the follow-up activity entails.';
COMMENT ON COLUMN "public"."capa_follow_ups"."status" IS 'Current status of the follow-up activity (e.g., Scheduled, Completed).';
COMMENT ON COLUMN "public"."capa_follow_ups"."outcome" IS 'Outcome of the follow-up, detailing whether the CAPA actions were effective.';
COMMENT ON COLUMN "public"."capa_follow_ups"."conducted_by" IS 'ID of the user who conducted the follow-up activity.';
COMMENT ON COLUMN "public"."capa_follow_ups"."created_at" IS 'The timestamp when the follow-up record was created.';
COMMENT ON COLUMN "public"."capa_follow_ups"."updated_at" IS 'The timestamp when the follow-up record was last updated.';
COMMENT ON TABLE "public"."capa_follow_ups" IS 'Tracks scheduled and completed follow-up activities to verify the effectiveness of CAPA actions after their implementation.';

-- ----------------------------
-- Table structure for capa_risk_assessments
-- ----------------------------
DROP TABLE IF EXISTS "public"."capa_risk_assessments";
CREATE TABLE "public"."capa_risk_assessments" (
  "id" int4 NOT NULL DEFAULT nextval('capa_risk_assessments_id_seq'::regclass),
  "capa_id" int4 NOT NULL,
  "risk_description" text COLLATE "pg_catalog"."default",
  "risk_score" int4,
  "mitigation_plan" text COLLATE "pg_catalog"."default",
  "residual_risk_score" int4,
  "assessed_by" int4,
  "assessment_date" date,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."capa_risk_assessments"."id" IS 'The primary key identifier for a CAPA risk assessment record.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."capa_id" IS 'Foreign key that links to the corresponding CAPA record.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."risk_description" IS 'Description of the risk being assessed.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."risk_score" IS 'Numeric score representing the initial risk level before mitigation actions are applied.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."mitigation_plan" IS 'Description of the actions planned to mitigate the identified risk.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."residual_risk_score" IS 'Numeric score representing the risk level after mitigation actions have been implemented.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."assessed_by" IS 'ID of the user who conducted the risk assessment.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."assessment_date" IS 'The date on which the risk assessment was conducted.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."created_at" IS 'The timestamp when the risk assessment record was created.';
COMMENT ON COLUMN "public"."capa_risk_assessments"."updated_at" IS 'The timestamp when the risk assessment record was last updated.';
COMMENT ON TABLE "public"."capa_risk_assessments" IS 'Evaluates the risk associated with the issues that led to the CAPA and assesses the potential impact of the CAPA actions.';

-- ----------------------------
-- Table structure for capas
-- ----------------------------
DROP TABLE IF EXISTS "public"."capas";
CREATE TABLE "public"."capas" (
  "id" int4 NOT NULL DEFAULT nextval('capas_id_seq'::regclass),
  "deviation_id" int4,
  "description" text COLLATE "pg_catalog"."default" NOT NULL,
  "action_type" "public"."capa_type" NOT NULL DEFAULT 'CORRECTIVE'::capa_type,
  "status" "public"."capa_status" NOT NULL DEFAULT 'OPEN'::capa_status,
  "responsible_user_id" int4,
  "due_date" date,
  "completion_date" date,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."capas"."id" IS 'The primary key identifier for a CAPA record.';
COMMENT ON COLUMN "public"."capas"."deviation_id" IS 'Foreign key linking the CAPA to its related deviation record.';
COMMENT ON COLUMN "public"."capas"."description" IS 'Detailed description of the CAPA, explaining what actions are to be taken.';
COMMENT ON COLUMN "public"."capas"."action_type" IS 'Type of action, either corrective or preventive, defined by predefined enums.';
COMMENT ON COLUMN "public"."capas"."status" IS 'Current status of the CAPA, such as Open, In Progress, Completed.';
COMMENT ON COLUMN "public"."capas"."responsible_user_id" IS 'Foreign key linking the CAPA to the responsible user or owner.';
COMMENT ON COLUMN "public"."capas"."due_date" IS 'The planned due date for the CAPA completion.';
COMMENT ON COLUMN "public"."capas"."completion_date" IS 'The actual date when the CAPA was completed.';
COMMENT ON COLUMN "public"."capas"."created_at" IS 'The timestamp when the CAPA record was created.';
COMMENT ON COLUMN "public"."capas"."updated_at" IS 'The timestamp when the CAPA record was last updated.';
COMMENT ON TABLE "public"."capas" IS 'Stores details about corrective and preventive actions (CAPA) taken in response to deviations or proactive measures.';

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS "public"."comments";
CREATE TABLE "public"."comments" (
  "id" int4 NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
  "content" text COLLATE "pg_catalog"."default" NOT NULL,
  "author_id" int4,
  "related_table" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "related_id" int4 NOT NULL,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."comments"."id" IS 'The primary key identifier for a comment.';
COMMENT ON COLUMN "public"."comments"."content" IS 'The text content of the comment.';
COMMENT ON COLUMN "public"."comments"."author_id" IS 'Foreign key linking the comment to the user who authored it.';
COMMENT ON COLUMN "public"."comments"."related_table" IS 'The name of the table to which the comment is related, e.g., deviations, capas.';
COMMENT ON COLUMN "public"."comments"."related_id" IS 'The primary key ID of the record in the related table that the comment is associated with.';
COMMENT ON COLUMN "public"."comments"."created_at" IS 'The timestamp when the comment was created.';
COMMENT ON COLUMN "public"."comments"."updated_at" IS 'The timestamp when the comment was last updated.';
COMMENT ON TABLE "public"."comments" IS 'Stores comments made by users on various records like deviations, CAPAs, etc., facilitating discussion and documentation of thoughts and feedback.';

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS "public"."departments";
CREATE TABLE "public"."departments" (
  "id" int4 NOT NULL DEFAULT nextval('departments_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."departments"."id" IS 'The primary key identifier for a department.';
COMMENT ON COLUMN "public"."departments"."name" IS 'The name of the department, must be unique.';
COMMENT ON COLUMN "public"."departments"."description" IS 'A brief description of what the department handles or is responsible for.';
COMMENT ON COLUMN "public"."departments"."created_at" IS 'Timestamp when the department record was created.';
COMMENT ON COLUMN "public"."departments"."updated_at" IS 'Timestamp when the department record was last updated.';
COMMENT ON TABLE "public"."departments" IS 'Stores information about the organizational departments.';

-- ----------------------------
-- Table structure for deviation_batches
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_batches";
CREATE TABLE "public"."deviation_batches" (
  "deviation_id" int4 NOT NULL,
  "batch_id" int4 NOT NULL
)
;
COMMENT ON TABLE "public"."deviation_batches" IS 'Maps deviations to multiple batches, accommodating deviations that involve multiple batches.';

-- ----------------------------
-- Table structure for deviation_closure_approvals
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_closure_approvals";
CREATE TABLE "public"."deviation_closure_approvals" (
  "id" int4 NOT NULL DEFAULT nextval('deviation_closure_approvals_id_seq'::regclass),
  "deviation_id" int4 NOT NULL,
  "approved_by_user_id" int4 NOT NULL,
  "approval_date" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "comments" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "public"."deviation_closure_approvals" IS 'Stores approval records for closing deviations, including approver ID, date, and comments.';

-- ----------------------------
-- Table structure for deviation_departments
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_departments";
CREATE TABLE "public"."deviation_departments" (
  "deviation_id" int4 NOT NULL,
  "department_id" int4 NOT NULL
)
;
COMMENT ON TABLE "public"."deviation_departments" IS 'Maps deviations to multiple departments, accommodating deviations that impact more than one department.';

-- ----------------------------
-- Table structure for deviation_equipments
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_equipments";
CREATE TABLE "public"."deviation_equipments" (
  "deviation_id" int4 NOT NULL,
  "equipment_id" int4 NOT NULL
)
;
COMMENT ON TABLE "public"."deviation_equipments" IS 'Maps deviations to multiple equipments, accommodating deviations that involve multiple pieces of equipment.';

-- ----------------------------
-- Table structure for deviation_products
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_products";
CREATE TABLE "public"."deviation_products" (
  "deviation_id" int4 NOT NULL,
  "product_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."deviation_products"."deviation_id" IS 'Foreign key that links to the deviations table, part of the composite primary key.';
COMMENT ON COLUMN "public"."deviation_products"."product_id" IS 'Foreign key that links to the products table, part of the composite primary key.';
COMMENT ON TABLE "public"."deviation_products" IS 'Linking table to manage many-to-many relationships between deviations and products. This supports scenarios where a single deviation may affect multiple products or multiple deviations may involve a single product.';

-- ----------------------------
-- Table structure for deviation_reviewers
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviation_reviewers";
CREATE TABLE "public"."deviation_reviewers" (
  "id" int4 NOT NULL DEFAULT nextval('deviation_reviewers_id_seq'::regclass),
  "deviation_id" int4 NOT NULL,
  "reviewer_id" int4 NOT NULL,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON TABLE "public"."deviation_reviewers" IS 'Maps reviewers (users) to specific deviations for the purpose of conducting cross-functional reviews.';

-- ----------------------------
-- Table structure for deviations
-- ----------------------------
DROP TABLE IF EXISTS "public"."deviations";
CREATE TABLE "public"."deviations" (
  "id" int4 NOT NULL DEFAULT nextval('deviations_id_seq'::regclass),
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default" NOT NULL,
  "status" "public"."deviation_status" NOT NULL DEFAULT 'OPEN'::deviation_status,
  "initiated_by_user_id" int4 NOT NULL,
  "department_id" int4 NOT NULL,
  "date_of_occurrence" date NOT NULL,
  "date_of_identification" date NOT NULL,
  "time_of_identification" time(6),
  "date_of_initiation" date NOT NULL DEFAULT CURRENT_DATE,
  "justification_for_delay" text COLLATE "pg_catalog"."default",
  "event_related_type" "public"."event_related_enum" NOT NULL,
  "document_details" text COLLATE "pg_catalog"."default",
  "other_details" text COLLATE "pg_catalog"."default",
  "risk_assessment_product" text COLLATE "pg_catalog"."default",
  "risk_assessment_facility" text COLLATE "pg_catalog"."default",
  "risk_assessment_equipment" text COLLATE "pg_catalog"."default",
  "risk_assessment_others" text COLLATE "pg_catalog"."default",
  "impact_on_batches" bool DEFAULT false,
  "impact_on_other_batches" bool DEFAULT false,
  "file_attachments" jsonb,
  "remarks" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."deviations"."id" IS 'The primary key identifier for a deviation record.';
COMMENT ON COLUMN "public"."deviations"."title" IS 'The title or brief description of the deviation.';
COMMENT ON COLUMN "public"."deviations"."description" IS 'Detailed description of the deviation incident.';
COMMENT ON COLUMN "public"."deviations"."status" IS 'The current state of the deviation, using the deviation_status enum (e.g., OPEN, CLOSED, UNDER_INVESTIGATION).';
COMMENT ON COLUMN "public"."deviations"."initiated_by_user_id" IS 'Foreign key linked to the users table, identifying the user who initiated the deviation record.';
COMMENT ON COLUMN "public"."deviations"."department_id" IS 'Foreign key linked to the departments table, indicating the department where the deviation occurred.';
COMMENT ON COLUMN "public"."deviations"."date_of_occurrence" IS 'The date when the deviation actually occurred.';
COMMENT ON COLUMN "public"."deviations"."date_of_identification" IS 'The date when the deviation was identified.';
COMMENT ON COLUMN "public"."deviations"."time_of_identification" IS 'The time when the deviation was identified.';
COMMENT ON COLUMN "public"."deviations"."date_of_initiation" IS 'The date when the deviation report was officially initiated.';
COMMENT ON COLUMN "public"."deviations"."justification_for_delay" IS 'Justification required if the deviation report initiation exceeds 24 hours from identification.';
COMMENT ON COLUMN "public"."deviations"."event_related_type" IS 'Type of event related to the deviation, categorized by product, material, etc.';
COMMENT ON COLUMN "public"."deviations"."document_details" IS 'Details about any documents related to the deviation.';
COMMENT ON COLUMN "public"."deviations"."other_details" IS 'Details about other types of events related to the deviation.';
COMMENT ON COLUMN "public"."deviations"."risk_assessment_product" IS 'Risk assessment details for the product involved in the deviation.';
COMMENT ON COLUMN "public"."deviations"."risk_assessment_facility" IS 'Risk assessment details for the facility involved in the deviation.';
COMMENT ON COLUMN "public"."deviations"."risk_assessment_equipment" IS 'Risk assessment details for the equipment involved in the deviation.';
COMMENT ON COLUMN "public"."deviations"."risk_assessment_others" IS 'Risk assessment details for other aspects involved in the deviation.';
COMMENT ON COLUMN "public"."deviations"."impact_on_batches" IS 'Flag indicating if there is an impact on the batches involved.';
COMMENT ON COLUMN "public"."deviations"."impact_on_other_batches" IS 'Flag indicating if there is an impact on other batches not directly involved.';
COMMENT ON COLUMN "public"."deviations"."file_attachments" IS 'JSONB field to store metadata of attached files.';
COMMENT ON COLUMN "public"."deviations"."remarks" IS 'Additional remarks by the user submitting the deviation report.';
COMMENT ON COLUMN "public"."deviations"."created_at" IS 'Timestamp when the deviation record was created.';
COMMENT ON COLUMN "public"."deviations"."updated_at" IS 'Timestamp when the deviation record was last updated.';
COMMENT ON TABLE "public"."deviations" IS 'Stores records of deviations from standard procedures or specifications within the organization.';

-- ----------------------------
-- Table structure for dmaic_steps
-- ----------------------------
DROP TABLE IF EXISTS "public"."dmaic_steps";
CREATE TABLE "public"."dmaic_steps" (
  "id" int4 NOT NULL DEFAULT nextval('dmaic_steps_id_seq'::regclass),
  "investigation_id" int4 NOT NULL,
  "phase" varchar(255) COLLATE "pg_catalog"."default",
  "description" text COLLATE "pg_catalog"."default",
  "findings" text COLLATE "pg_catalog"."default",
  "recommendations" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."dmaic_steps"."phase" IS 'The phase of DMAIC being documented (Define, Measure, Analyze, Improve, Control).';
COMMENT ON COLUMN "public"."dmaic_steps"."description" IS 'Description of activities and focus during the phase.';
COMMENT ON COLUMN "public"."dmaic_steps"."findings" IS 'Key findings from the phase’s activities.';
COMMENT ON COLUMN "public"."dmaic_steps"."recommendations" IS 'Recommendations based on the findings for future improvements.';
COMMENT ON TABLE "public"."dmaic_steps" IS 'Tracks each phase of DMAIC analysis within an investigation, detailing actions and findings in each phase.';

-- ----------------------------
-- Table structure for equipments
-- ----------------------------
DROP TABLE IF EXISTS "public"."equipments";
CREATE TABLE "public"."equipments" (
  "id" int4 NOT NULL DEFAULT nextval('equipments_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "category" "public"."equipment_category",
  "status" "public"."equipment_status" NOT NULL DEFAULT 'OPERATIONAL'::equipment_status,
  "location" varchar(255) COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."equipments"."id" IS 'The primary key identifier for an equipment.';
COMMENT ON COLUMN "public"."equipments"."name" IS 'The name of the equipment.';
COMMENT ON COLUMN "public"."equipments"."description" IS 'A detailed description of the equipment, including its capabilities and uses.';
COMMENT ON COLUMN "public"."equipments"."category" IS 'The category of the equipment, categorized by predefined enums.';
COMMENT ON COLUMN "public"."equipments"."status" IS 'The operational status of the equipment, e.g., OPERATIONAL, UNDER_MAINTENANCE.';
COMMENT ON COLUMN "public"."equipments"."location" IS 'The physical location of the equipment within the facility.';
COMMENT ON COLUMN "public"."equipments"."created_at" IS 'The timestamp when the equipment record was created.';
COMMENT ON COLUMN "public"."equipments"."updated_at" IS 'The timestamp when the equipment record was last updated.';
COMMENT ON TABLE "public"."equipments" IS 'Stores information about various equipment used within the organization for production or testing.';

-- ----------------------------
-- Table structure for fishbone_steps
-- ----------------------------
DROP TABLE IF EXISTS "public"."fishbone_steps";
CREATE TABLE "public"."fishbone_steps" (
  "id" int4 NOT NULL DEFAULT nextval('fishbone_steps_id_seq'::regclass),
  "investigation_id" int4 NOT NULL,
  "category" varchar(255) COLLATE "pg_catalog"."default",
  "description" text COLLATE "pg_catalog"."default",
  "cause" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."fishbone_steps"."category" IS 'Category of the cause identified in the Fishbone analysis (e.g., Manpower, Machinery, Methods, Materials, Measurement, Environment).';
COMMENT ON COLUMN "public"."fishbone_steps"."description" IS 'Description of the issue or sub-issue identified in the category.';
COMMENT ON COLUMN "public"."fishbone_steps"."cause" IS 'Detailed explanation of the cause contributing to the problem.';
COMMENT ON TABLE "public"."fishbone_steps" IS 'Tracks the steps and causes identified in a Fishbone (Ishikawa) analysis within an investigation.';

-- ----------------------------
-- Table structure for five_whys_steps
-- ----------------------------
DROP TABLE IF EXISTS "public"."five_whys_steps";
CREATE TABLE "public"."five_whys_steps" (
  "id" int4 NOT NULL DEFAULT nextval('five_whys_steps_id_seq'::regclass),
  "investigation_id" int4 NOT NULL,
  "why_number" int4 NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "cause" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."five_whys_steps"."why_number" IS 'Indicates the sequential number of the "why" in the analysis.';
COMMENT ON COLUMN "public"."five_whys_steps"."description" IS 'Description of the step or query in the 5 Why analysis.';
COMMENT ON COLUMN "public"."five_whys_steps"."cause" IS 'Identified cause at this step of the analysis.';
COMMENT ON TABLE "public"."five_whys_steps" IS 'Tracks the steps of the 5 Why analysis method within an investigation.';

-- ----------------------------
-- Table structure for investigation_investigators
-- ----------------------------
DROP TABLE IF EXISTS "public"."investigation_investigators";
CREATE TABLE "public"."investigation_investigators" (
  "investigation_id" int4 NOT NULL,
  "investigator_id" int4 NOT NULL,
  "assigned_date" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON TABLE "public"."investigation_investigators" IS 'Links multiple investigators to an investigation, facilitating team investigations and collaborative work.';

-- ----------------------------
-- Table structure for investigations
-- ----------------------------
DROP TABLE IF EXISTS "public"."investigations";
CREATE TABLE "public"."investigations" (
  "id" int4 NOT NULL DEFAULT nextval('investigations_id_seq'::regclass),
  "deviation_id" int4 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "description" text COLLATE "pg_catalog"."default",
  "status" "public"."investigation_status_enum" NOT NULL DEFAULT 'OPEN'::investigation_status_enum,
  "started_at" timestamp(6),
  "closed_at" timestamp(6),
  "lead_investigator_id" int4,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "methodology" "public"."investigation_methodology_enum"
)
;
COMMENT ON COLUMN "public"."investigations"."id" IS 'The primary key identifier for an investigation.';
COMMENT ON COLUMN "public"."investigations"."deviation_id" IS 'Foreign key linking to the deviation that triggered this investigation.';
COMMENT ON COLUMN "public"."investigations"."title" IS 'Title of the investigation for quick reference.';
COMMENT ON COLUMN "public"."investigations"."description" IS 'Detailed description of the investigation scope and purpose.';
COMMENT ON COLUMN "public"."investigations"."status" IS 'Current status of the investigation.';
COMMENT ON COLUMN "public"."investigations"."started_at" IS 'Timestamp when the investigation was formally started.';
COMMENT ON COLUMN "public"."investigations"."closed_at" IS 'Timestamp when the investigation was formally closed.';
COMMENT ON COLUMN "public"."investigations"."lead_investigator_id" IS 'User ID of the lead investigator managing this investigation.';
COMMENT ON COLUMN "public"."investigations"."created_at" IS 'Timestamp when the investigation record was created.';
COMMENT ON COLUMN "public"."investigations"."updated_at" IS 'Timestamp when the investigation record was last updated.';
COMMENT ON COLUMN "public"."investigations"."methodology" IS 'Specifies the methodology used for the investigation, such as 5 Why, Fishbone, ADKOM, or DMAIC.';
COMMENT ON TABLE "public"."investigations" IS 'Tracks the overarching details of investigations related to deviations or incidents.';

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS "public"."materials";
CREATE TABLE "public"."materials" (
  "id" int4 NOT NULL DEFAULT nextval('materials_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "category" "public"."material_category",
  "status" "public"."material_status" NOT NULL DEFAULT 'IN_STOCK'::material_status,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."materials"."id" IS 'The primary key identifier for a material.';
COMMENT ON COLUMN "public"."materials"."name" IS 'The name of the material.';
COMMENT ON COLUMN "public"."materials"."description" IS 'A detailed description of the material and its properties.';
COMMENT ON COLUMN "public"."materials"."category" IS 'The category of the material, classified by predefined enums.';
COMMENT ON COLUMN "public"."materials"."status" IS 'The status of the material, e.g., IN_STOCK, OUT_OF_STOCK.';
COMMENT ON COLUMN "public"."materials"."created_at" IS 'The timestamp when the material record was created.';
COMMENT ON COLUMN "public"."materials"."updated_at" IS 'The timestamp when the material record was last updated.';
COMMENT ON TABLE "public"."materials" IS 'Stores information about materials used in the manufacturing process or related to products.';

-- ----------------------------
-- Table structure for notifications
-- ----------------------------
DROP TABLE IF EXISTS "public"."notifications";
CREATE TABLE "public"."notifications" (
  "id" int4 NOT NULL DEFAULT nextval('notifications_id_seq'::regclass),
  "user_id" int4 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "message" text COLLATE "pg_catalog"."default" NOT NULL,
  "status" "public"."notification_status" NOT NULL DEFAULT 'UNREAD'::notification_status,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."notifications"."id" IS 'The primary key identifier for a notification.';
COMMENT ON COLUMN "public"."notifications"."user_id" IS 'Foreign key linked to the users table; identifies the user to whom the notification is directed.';
COMMENT ON COLUMN "public"."notifications"."title" IS 'The title of the notification.';
COMMENT ON COLUMN "public"."notifications"."message" IS 'The content or message of the notification.';
COMMENT ON COLUMN "public"."notifications"."status" IS 'The current state of the notification, using the notification_status enum (e.g., UNREAD, READ).';
COMMENT ON COLUMN "public"."notifications"."created_at" IS 'Timestamp when the notification was created.';
COMMENT ON COLUMN "public"."notifications"."updated_at" IS 'Timestamp when the notification was last updated.';
COMMENT ON TABLE "public"."notifications" IS 'Stores notifications sent to users within the system, tracking their read/unread status.';

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS "public"."products";
CREATE TABLE "public"."products" (
  "id" int4 NOT NULL DEFAULT nextval('products_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "category" "public"."product_category",
  "status" "public"."product_status" NOT NULL DEFAULT 'ACTIVE'::product_status,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."products"."id" IS 'The primary key identifier for a product.';
COMMENT ON COLUMN "public"."products"."name" IS 'The name of the product.';
COMMENT ON COLUMN "public"."products"."description" IS 'A detailed description of the product, including its use and any important characteristics.';
COMMENT ON COLUMN "public"."products"."category" IS 'Category or type of the product, categorized by predefined enums.';
COMMENT ON COLUMN "public"."products"."status" IS 'The production status of the product, using the product_status enum.';
COMMENT ON COLUMN "public"."products"."created_at" IS 'The timestamp when the product record was created.';
COMMENT ON COLUMN "public"."products"."updated_at" IS 'The timestamp when the product record was last updated.';
COMMENT ON TABLE "public"."products" IS 'Stores information about products manufactured, stored, or monitored by the organization.';

-- ----------------------------
-- Table structure for qa_deviation_risk_assessments
-- ----------------------------
DROP TABLE IF EXISTS "public"."qa_deviation_risk_assessments";
CREATE TABLE "public"."qa_deviation_risk_assessments" (
  "id" int4 NOT NULL DEFAULT nextval('qa_deviation_risk_assessments_id_seq'::regclass),
  "deviation_id" int4 NOT NULL,
  "factor_type" "public"."risk_factor_type",
  "justification" text COLLATE "pg_catalog"."default",
  "score" int4
)
;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
  "id" int4 NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" text COLLATE "pg_catalog"."default",
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."roles"."id" IS 'The primary key identifier for a role.';
COMMENT ON COLUMN "public"."roles"."name" IS 'The name of the role, unique to ensure no duplicate roles.';
COMMENT ON COLUMN "public"."roles"."description" IS 'A description of the role, outlining the permissions and responsibilities associated with it.';
COMMENT ON COLUMN "public"."roles"."created_at" IS 'Timestamp when the role record was created.';
COMMENT ON COLUMN "public"."roles"."updated_at" IS 'Timestamp when the role record was last updated.';
COMMENT ON TABLE "public"."roles" IS 'Stores different roles within the system, defining access levels and permissions for each role.';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int4 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "role_id" int4 NOT NULL,
  "department_id" int4 NOT NULL,
  "first_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "last_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "phone_number" varchar(50) COLLATE "pg_catalog"."default",
  "status_id" int4 NOT NULL,
  "created_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."users"."id" IS 'The primary key identifier for a user.';
COMMENT ON COLUMN "public"."users"."username" IS 'Unique username for user login.';
COMMENT ON COLUMN "public"."users"."password" IS 'Hashed password for user login.';
COMMENT ON COLUMN "public"."users"."role_id" IS 'Foreign key linked to the roles table.';
COMMENT ON COLUMN "public"."users"."department_id" IS 'Foreign key linked to the departments table indicating where the user belongs.';
COMMENT ON COLUMN "public"."users"."first_name" IS 'First name of the user.';
COMMENT ON COLUMN "public"."users"."last_name" IS 'Last name of the user.';
COMMENT ON COLUMN "public"."users"."email" IS 'User''s email address, must be unique.';
COMMENT ON COLUMN "public"."users"."phone_number" IS 'User''s contact phone number.';
COMMENT ON COLUMN "public"."users"."status_id" IS 'Foreign key linked to the user_status table, representing the user''s account status (e.g., active, inactive).';
COMMENT ON COLUMN "public"."users"."created_at" IS 'Timestamp when the user record was created.';
COMMENT ON COLUMN "public"."users"."updated_at" IS 'Timestamp when the user record was last updated.';
COMMENT ON TABLE "public"."users" IS 'Stores information about users including login credentials, roles, and personal details.';

-- ----------------------------
-- Function structure for update_updated_at_column
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."update_updated_at_column"();
CREATE OR REPLACE FUNCTION "public"."update_updated_at_column"()
  RETURNS "pg_catalog"."trigger" AS $BODY$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."adkom_steps_id_seq"
OWNED BY "public"."adkom_steps"."id";
SELECT setval('"public"."adkom_steps_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."batches_id_seq"
OWNED BY "public"."batches"."id";
SELECT setval('"public"."batches_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."capa_actions_id_seq"
OWNED BY "public"."capa_actions"."id";
SELECT setval('"public"."capa_actions_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."capa_files_id_seq"
OWNED BY "public"."capa_files"."id";
SELECT setval('"public"."capa_files_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."capa_follow_ups_id_seq"
OWNED BY "public"."capa_follow_ups"."id";
SELECT setval('"public"."capa_follow_ups_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."capa_risk_assessments_id_seq"
OWNED BY "public"."capa_risk_assessments"."id";
SELECT setval('"public"."capa_risk_assessments_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."capas_id_seq"
OWNED BY "public"."capas"."id";
SELECT setval('"public"."capas_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."comments_id_seq"
OWNED BY "public"."comments"."id";
SELECT setval('"public"."comments_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."departments_id_seq"
OWNED BY "public"."departments"."id";
SELECT setval('"public"."departments_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."deviation_closure_approvals_id_seq"
OWNED BY "public"."deviation_closure_approvals"."id";
SELECT setval('"public"."deviation_closure_approvals_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."deviation_reviewers_id_seq"
OWNED BY "public"."deviation_reviewers"."id";
SELECT setval('"public"."deviation_reviewers_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."deviations_id_seq"
OWNED BY "public"."deviations"."id";
SELECT setval('"public"."deviations_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."dmaic_steps_id_seq"
OWNED BY "public"."dmaic_steps"."id";
SELECT setval('"public"."dmaic_steps_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."equipments_id_seq"
OWNED BY "public"."equipments"."id";
SELECT setval('"public"."equipments_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."fishbone_steps_id_seq"
OWNED BY "public"."fishbone_steps"."id";
SELECT setval('"public"."fishbone_steps_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."five_whys_steps_id_seq"
OWNED BY "public"."five_whys_steps"."id";
SELECT setval('"public"."five_whys_steps_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."investigations_id_seq"
OWNED BY "public"."investigations"."id";
SELECT setval('"public"."investigations_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."materials_id_seq"
OWNED BY "public"."materials"."id";
SELECT setval('"public"."materials_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."notifications_id_seq"
OWNED BY "public"."notifications"."id";
SELECT setval('"public"."notifications_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."products_id_seq"
OWNED BY "public"."products"."id";
SELECT setval('"public"."products_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."qa_deviation_risk_assessments_id_seq"
OWNED BY "public"."qa_deviation_risk_assessments"."id";
SELECT setval('"public"."qa_deviation_risk_assessments_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."roles_id_seq"
OWNED BY "public"."roles"."id";
SELECT setval('"public"."roles_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."users_id_seq"
OWNED BY "public"."users"."id";
SELECT setval('"public"."users_id_seq"', 1, false);

-- ----------------------------
-- Triggers structure for table adkom_steps
-- ----------------------------
CREATE TRIGGER "update_adkom_steps_updated_at" BEFORE UPDATE ON "public"."adkom_steps"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table adkom_steps
-- ----------------------------
ALTER TABLE "public"."adkom_steps" ADD CONSTRAINT "adkom_steps_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table batches
-- ----------------------------
CREATE TRIGGER "update_batches_updated_at" BEFORE UPDATE ON "public"."batches"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table batches
-- ----------------------------
ALTER TABLE "public"."batches" ADD CONSTRAINT "batches_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table capa_actions
-- ----------------------------
CREATE TRIGGER "update_capa_actions_updated_at" BEFORE UPDATE ON "public"."capa_actions"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table capa_actions
-- ----------------------------
ALTER TABLE "public"."capa_actions" ADD CONSTRAINT "capa_actions_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table capa_files
-- ----------------------------
CREATE TRIGGER "update_capa_files_updated_at" BEFORE UPDATE ON "public"."capa_files"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table capa_files
-- ----------------------------
ALTER TABLE "public"."capa_files" ADD CONSTRAINT "capa_files_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table capa_follow_ups
-- ----------------------------
CREATE TRIGGER "update_capa_follow_ups_updated_at" BEFORE UPDATE ON "public"."capa_follow_ups"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table capa_follow_ups
-- ----------------------------
ALTER TABLE "public"."capa_follow_ups" ADD CONSTRAINT "capa_follow_ups_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table capa_risk_assessments
-- ----------------------------
CREATE TRIGGER "update_capa_risk_assessments_updated_at" BEFORE UPDATE ON "public"."capa_risk_assessments"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table capa_risk_assessments
-- ----------------------------
ALTER TABLE "public"."capa_risk_assessments" ADD CONSTRAINT "capa_risk_assessments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table capas
-- ----------------------------
CREATE TRIGGER "update_capas_updated_at" BEFORE UPDATE ON "public"."capas"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table capas
-- ----------------------------
ALTER TABLE "public"."capas" ADD CONSTRAINT "capas_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table comments
-- ----------------------------
CREATE TRIGGER "update_comments_updated_at" BEFORE UPDATE ON "public"."comments"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table comments
-- ----------------------------
ALTER TABLE "public"."comments" ADD CONSTRAINT "comments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table departments
-- ----------------------------
CREATE TRIGGER "update_departments_updated_at" BEFORE UPDATE ON "public"."departments"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Uniques structure for table departments
-- ----------------------------
ALTER TABLE "public"."departments" ADD CONSTRAINT "departments_name_key" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table departments
-- ----------------------------
ALTER TABLE "public"."departments" ADD CONSTRAINT "departments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table deviation_batches
-- ----------------------------
ALTER TABLE "public"."deviation_batches" ADD CONSTRAINT "deviation_batches_pkey" PRIMARY KEY ("deviation_id", "batch_id");

-- ----------------------------
-- Primary Key structure for table deviation_closure_approvals
-- ----------------------------
ALTER TABLE "public"."deviation_closure_approvals" ADD CONSTRAINT "deviation_closure_approvals_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table deviation_departments
-- ----------------------------
ALTER TABLE "public"."deviation_departments" ADD CONSTRAINT "deviation_departments_pkey" PRIMARY KEY ("deviation_id", "department_id");

-- ----------------------------
-- Primary Key structure for table deviation_equipments
-- ----------------------------
ALTER TABLE "public"."deviation_equipments" ADD CONSTRAINT "deviation_equipments_pkey" PRIMARY KEY ("deviation_id", "equipment_id");

-- ----------------------------
-- Primary Key structure for table deviation_products
-- ----------------------------
ALTER TABLE "public"."deviation_products" ADD CONSTRAINT "deviation_products_pkey" PRIMARY KEY ("deviation_id", "product_id");

-- ----------------------------
-- Primary Key structure for table deviation_reviewers
-- ----------------------------
ALTER TABLE "public"."deviation_reviewers" ADD CONSTRAINT "deviation_reviewers_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table deviations
-- ----------------------------
CREATE TRIGGER "update_deviations_updated_at" BEFORE UPDATE ON "public"."deviations"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table deviations
-- ----------------------------
ALTER TABLE "public"."deviations" ADD CONSTRAINT "deviations_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table dmaic_steps
-- ----------------------------
CREATE TRIGGER "update_dmaic_steps_updated_at" BEFORE UPDATE ON "public"."dmaic_steps"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table dmaic_steps
-- ----------------------------
ALTER TABLE "public"."dmaic_steps" ADD CONSTRAINT "dmaic_steps_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table equipments
-- ----------------------------
CREATE TRIGGER "update_equipments_updated_at" BEFORE UPDATE ON "public"."equipments"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table equipments
-- ----------------------------
ALTER TABLE "public"."equipments" ADD CONSTRAINT "equipments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table fishbone_steps
-- ----------------------------
CREATE TRIGGER "update_fishbone_steps_updated_at" BEFORE UPDATE ON "public"."fishbone_steps"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table fishbone_steps
-- ----------------------------
ALTER TABLE "public"."fishbone_steps" ADD CONSTRAINT "fishbone_steps_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table five_whys_steps
-- ----------------------------
CREATE TRIGGER "update_five_whys_steps_updated_at" BEFORE UPDATE ON "public"."five_whys_steps"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table five_whys_steps
-- ----------------------------
ALTER TABLE "public"."five_whys_steps" ADD CONSTRAINT "five_whys_steps_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table investigation_investigators
-- ----------------------------
ALTER TABLE "public"."investigation_investigators" ADD CONSTRAINT "investigation_investigators_pkey" PRIMARY KEY ("investigation_id", "investigator_id");

-- ----------------------------
-- Triggers structure for table investigations
-- ----------------------------
CREATE TRIGGER "update_investigations_updated_at" BEFORE UPDATE ON "public"."investigations"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table investigations
-- ----------------------------
ALTER TABLE "public"."investigations" ADD CONSTRAINT "investigations_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table materials
-- ----------------------------
CREATE TRIGGER "update_materials_updated_at" BEFORE UPDATE ON "public"."materials"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table materials
-- ----------------------------
ALTER TABLE "public"."materials" ADD CONSTRAINT "materials_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table notifications
-- ----------------------------
CREATE TRIGGER "update_notifications_updated_at" BEFORE UPDATE ON "public"."notifications"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table notifications
-- ----------------------------
ALTER TABLE "public"."notifications" ADD CONSTRAINT "notifications_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table products
-- ----------------------------
CREATE TRIGGER "update_products_updated_at" BEFORE UPDATE ON "public"."products"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Primary Key structure for table products
-- ----------------------------
ALTER TABLE "public"."products" ADD CONSTRAINT "products_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table qa_deviation_risk_assessments
-- ----------------------------
ALTER TABLE "public"."qa_deviation_risk_assessments" ADD CONSTRAINT "qa_deviation_risk_assessments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table roles
-- ----------------------------
CREATE TRIGGER "update_roles_updated_at" BEFORE UPDATE ON "public"."roles"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Uniques structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD CONSTRAINT "roles_name_key" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Triggers structure for table users
-- ----------------------------
CREATE TRIGGER "update_users_updated_at" BEFORE UPDATE ON "public"."users"
FOR EACH ROW
EXECUTE PROCEDURE "public"."update_updated_at_column"();

-- ----------------------------
-- Uniques structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_username_key" UNIQUE ("username");
ALTER TABLE "public"."users" ADD CONSTRAINT "users_email_key" UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table adkom_steps
-- ----------------------------
ALTER TABLE "public"."adkom_steps" ADD CONSTRAINT "adkom_steps_investigation_id_fkey" FOREIGN KEY ("investigation_id") REFERENCES "public"."investigations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table batches
-- ----------------------------
ALTER TABLE "public"."batches" ADD CONSTRAINT "batches_product_id_fkey" FOREIGN KEY ("product_id") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table capa_actions
-- ----------------------------
ALTER TABLE "public"."capa_actions" ADD CONSTRAINT "capa_actions_actionee_id_fkey" FOREIGN KEY ("actionee_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."capa_actions" ADD CONSTRAINT "capa_actions_capa_id_fkey" FOREIGN KEY ("capa_id") REFERENCES "public"."capas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table capa_files
-- ----------------------------
ALTER TABLE "public"."capa_files" ADD CONSTRAINT "capa_files_capa_id_fkey" FOREIGN KEY ("capa_id") REFERENCES "public"."capas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."capa_files" ADD CONSTRAINT "capa_files_uploaded_by_fkey" FOREIGN KEY ("uploaded_by") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table capa_follow_ups
-- ----------------------------
ALTER TABLE "public"."capa_follow_ups" ADD CONSTRAINT "capa_follow_ups_capa_id_fkey" FOREIGN KEY ("capa_id") REFERENCES "public"."capas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."capa_follow_ups" ADD CONSTRAINT "capa_follow_ups_conducted_by_fkey" FOREIGN KEY ("conducted_by") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table capa_risk_assessments
-- ----------------------------
ALTER TABLE "public"."capa_risk_assessments" ADD CONSTRAINT "capa_risk_assessments_assessed_by_fkey" FOREIGN KEY ("assessed_by") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."capa_risk_assessments" ADD CONSTRAINT "capa_risk_assessments_capa_id_fkey" FOREIGN KEY ("capa_id") REFERENCES "public"."capas" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table capas
-- ----------------------------
ALTER TABLE "public"."capas" ADD CONSTRAINT "capas_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."capas" ADD CONSTRAINT "capas_responsible_user_id_fkey" FOREIGN KEY ("responsible_user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table comments
-- ----------------------------
ALTER TABLE "public"."comments" ADD CONSTRAINT "comments_author_id_fkey" FOREIGN KEY ("author_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_batches
-- ----------------------------
ALTER TABLE "public"."deviation_batches" ADD CONSTRAINT "deviation_batches_batch_id_fkey" FOREIGN KEY ("batch_id") REFERENCES "public"."batches" ("id") ON DELETE RESTRICT ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_batches" ADD CONSTRAINT "deviation_batches_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_closure_approvals
-- ----------------------------
ALTER TABLE "public"."deviation_closure_approvals" ADD CONSTRAINT "deviation_closure_approvals_approved_by_user_id_fkey" FOREIGN KEY ("approved_by_user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_closure_approvals" ADD CONSTRAINT "deviation_closure_approvals_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_departments
-- ----------------------------
ALTER TABLE "public"."deviation_departments" ADD CONSTRAINT "deviation_departments_department_id_fkey" FOREIGN KEY ("department_id") REFERENCES "public"."departments" ("id") ON DELETE RESTRICT ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_departments" ADD CONSTRAINT "deviation_departments_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_equipments
-- ----------------------------
ALTER TABLE "public"."deviation_equipments" ADD CONSTRAINT "deviation_equipments_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_equipments" ADD CONSTRAINT "deviation_equipments_equipment_id_fkey" FOREIGN KEY ("equipment_id") REFERENCES "public"."equipments" ("id") ON DELETE RESTRICT ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_products
-- ----------------------------
ALTER TABLE "public"."deviation_products" ADD CONSTRAINT "deviation_products_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_products" ADD CONSTRAINT "deviation_products_product_id_fkey" FOREIGN KEY ("product_id") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviation_reviewers
-- ----------------------------
ALTER TABLE "public"."deviation_reviewers" ADD CONSTRAINT "deviation_reviewers_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."deviation_reviewers" ADD CONSTRAINT "deviation_reviewers_reviewer_id_fkey" FOREIGN KEY ("reviewer_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table deviations
-- ----------------------------
ALTER TABLE "public"."deviations" ADD CONSTRAINT "deviations_department_id_fkey" FOREIGN KEY ("department_id") REFERENCES "public"."departments" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."deviations" ADD CONSTRAINT "deviations_initiated_by_user_id_fkey" FOREIGN KEY ("initiated_by_user_id") REFERENCES "public"."users" ("id") ON DELETE SET NULL ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dmaic_steps
-- ----------------------------
ALTER TABLE "public"."dmaic_steps" ADD CONSTRAINT "dmaic_steps_investigation_id_fkey" FOREIGN KEY ("investigation_id") REFERENCES "public"."investigations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table fishbone_steps
-- ----------------------------
ALTER TABLE "public"."fishbone_steps" ADD CONSTRAINT "fishbone_steps_investigation_id_fkey" FOREIGN KEY ("investigation_id") REFERENCES "public"."investigations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table five_whys_steps
-- ----------------------------
ALTER TABLE "public"."five_whys_steps" ADD CONSTRAINT "five_whys_steps_investigation_id_fkey" FOREIGN KEY ("investigation_id") REFERENCES "public"."investigations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table investigation_investigators
-- ----------------------------
ALTER TABLE "public"."investigation_investigators" ADD CONSTRAINT "investigation_investigators_investigation_id_fkey" FOREIGN KEY ("investigation_id") REFERENCES "public"."investigations" ("id") ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE "public"."investigation_investigators" ADD CONSTRAINT "investigation_investigators_investigator_id_fkey" FOREIGN KEY ("investigator_id") REFERENCES "public"."users" ("id") ON DELETE RESTRICT ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table investigations
-- ----------------------------
ALTER TABLE "public"."investigations" ADD CONSTRAINT "investigations_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."investigations" ADD CONSTRAINT "investigations_lead_investigator_id_fkey" FOREIGN KEY ("lead_investigator_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table notifications
-- ----------------------------
ALTER TABLE "public"."notifications" ADD CONSTRAINT "notifications_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table qa_deviation_risk_assessments
-- ----------------------------
ALTER TABLE "public"."qa_deviation_risk_assessments" ADD CONSTRAINT "qa_deviation_risk_assessments_deviation_id_fkey" FOREIGN KEY ("deviation_id") REFERENCES "public"."deviations" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
